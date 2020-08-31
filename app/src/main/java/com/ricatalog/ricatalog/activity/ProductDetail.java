package com.ricatalog.ricatalog.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.ricatalog.ricatalog.R;
import com.ricatalog.ricatalog.adapter.SliderAdapterExample;
import com.ricatalog.ricatalog.database.CartEntity;
import com.ricatalog.ricatalog.utils.Utils;
import com.ricatalog.ricatalog.viewmodel.CartViewModel;
import com.ricatalog.ricatalog.viewmodel.ProductDetailViewModel;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProductDetail extends AppCompatActivity {
@BindView(R.id.imageSlider)
    SliderView sliderView;
@BindView(R.id.title)
TextView title;
@BindView(R.id.code)
TextView code;
@BindView(R.id.qty)
TextView qty;
@BindView(R.id.color)
TextView color;
@BindView(R.id.polish)
TextView polish;
@BindView(R.id.addcart)
Button addcart;
    @BindView(R.id.shimmer_view_container)
    ShimmerFrameLayout shimmerFrameLayout;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.addprogress)
    ProgressBar progressBar;
    int mCartCount=0;
    int pos=0;
    TextView cartvalue;
    CartViewModel cartviewmodel;
    ProductDetailViewModel viewModel;
    SliderAdapterExample sliderAdapterExample;
    List<com.ricatalog.ricatalog.network.ProductDetail.ProductDetailList> details;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        ButterKnife.bind(this);
        shimmerFrameLayout.startShimmer();
        toolbar.setTitle("Product Detail");
        setSupportActionBar(toolbar);
        sliderView.setCurrentPageListener(new SliderView.OnSliderPageListener() {
            @Override
            public void onSliderPageChanged(int position) {
                    pos=position;
                setDetails(position);
            }
        });
        String id=getIntent().getStringExtra("id");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setTitleTextColor(getResources().getColor(R.color.whitecolor));
        sliderAdapterExample=new SliderAdapterExample(ProductDetail.this);
        sliderView.setSliderAdapter(sliderAdapterExample);
        viewModel=new ViewModelProvider(this).get(ProductDetailViewModel.class);
        viewModel.getListDetails(id);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM); //set indicator animation by using IndicatorAnimationType. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_RIGHT);
        sliderView.setIndicatorSelectedColor(Color.WHITE);
        sliderView.setIndicatorUnselectedColor(Color.GRAY);
        sliderView.startAutoCycle();
        sliderView.setScrollTimeInSec(7); //set scroll delay in seconds :
        cartviewmodel= new ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(CartViewModel.class);
        viewModel.getList().observe(this, new Observer<com.ricatalog.ricatalog.network.ProductDetail>() {
            @Override
            public void onChanged(com.ricatalog.ricatalog.network.ProductDetail productDetail) {
                if (productDetail != null) {
                    shimmerFrameLayout.stopShimmer();
                    shimmerFrameLayout.setVisibility(View.GONE);
                    details = productDetail.getGetdatalist();
                    setDetails(0);
                    sliderAdapterExample.setAdapter(productDetail.getGetdatalist());
                }
            }
        });
        viewModel.getDatapos().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {

            }
        });
        cartviewmodel.getEntityCount().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                mCartCount=integer;
                setupBadge();
            }
        });

        cartviewmodel.getEntity().observe(this, new Observer<List<CartEntity>>() {
            @Override
            public void onChanged(List<CartEntity> cartEntities) {
                if(cartEntities!=null)
                {
                    progressBar.setVisibility(View.GONE);
                    addcart.setVisibility(View.VISIBLE);


                }
            }
        });
    }
public void setDetails(int pos)
{
title.setText(details.get(pos).getTitle());
    code.setText(details.get(pos).getCode());
    qty.setText(details.get(pos).getQty());
    color.setText(details.get(pos).getColor());
    polish.setText(details.get(pos).getPolish());
}
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        final MenuItem menuItem = menu.findItem(R.id.action_cart);

        View actionView = menuItem.getActionView();
        cartvalue = (TextView) actionView.findViewById(R.id.cart_badge);

        setupBadge();

        actionView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOptionsItemSelected(menuItem);
            }
        });

        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.action_cart: {
                Intent i=new Intent(ProductDetail.this, CartActivity.class);
                startActivity(i);
                return true;
            }
        }

        return super.onOptionsItemSelected(item);
    }
    private void setupBadge() {

        if (cartvalue != null) {
            if (mCartCount == 0) {
                if (cartvalue.getVisibility() != View.GONE) {
                    cartvalue.setVisibility(View.GONE);
                }
            } else {
                cartvalue.setText(String.valueOf(Math.min(mCartCount, 99)));
                if (cartvalue.getVisibility() != View.VISIBLE) {
                    cartvalue.setVisibility(View.VISIBLE);
                }
            }
        }
    }
    @OnClick(R.id.addcart)
    public void addCart(View v)
    {
        progressBar.setVisibility(View.VISIBLE);
        addcart.setVisibility(View.GONE);
        cartviewmodel.insertProduct(new CartEntity(details.get(pos).getId(),details.get(pos).getTitle(),details.get(pos).getCode(),details.get(pos).getImage(),details.get(pos).getQty(),details.get(pos).getColor(),details.get(pos).getPolish(),"1"));
        Utils.showSuccesfulToast(ProductDetail.this,ProductDetail.this,"Added To Cart");
    }
}
