package com.ricatalog.ricatalog.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.snackbar.Snackbar;
import com.ricatalog.ricatalog.R;
import com.ricatalog.ricatalog.adapter.ProductAdapter;
import com.ricatalog.ricatalog.network.ProductCount;
import com.ricatalog.ricatalog.network.Products;
import com.ricatalog.ricatalog.utils.SpaceItemDecoration;
import com.ricatalog.ricatalog.viewmodel.MainActivityViewModel;
import com.ricatalog.ricatalog.viewmodel.ProductActivityViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductActivity extends AppCompatActivity {
@BindView(R.id.recyclerlayout)
RecyclerView recyclerView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
@BindView(R.id.shimmer_view_container)
    ShimmerFrameLayout shimmerFrameLayout;
ProductAdapter productAdapter;
ProductActivityViewModel viewModel;
public static String sid;
public static int COUNT;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        ButterKnife.bind(this);
        shimmerFrameLayout.startShimmer();
        toolbar.setTitle("Products");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        sid=getIntent().getStringExtra("sid");
        toolbar.setTitleTextColor(getResources().getColor(R.color.whitecolor));
        viewModel=new ViewModelProvider(this).get(ProductActivityViewModel.class);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(getApplicationContext(),2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.addItemDecoration(new SpaceItemDecoration(5));
        productAdapter=new ProductAdapter(ProductActivity.this);
        recyclerView.setAdapter(productAdapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(!recyclerView.canScrollVertically(1))
                {
                   // viewModel.getProducts(sid);
                }
            }
        });
        viewModel.getProductsCountNo(sid);
        viewModel.getProductCount().observe(this, new Observer<ProductCount>() {
            @Override
            public void onChanged(ProductCount productCount) {
                if (productCount != null) {
                    COUNT = productCount.getData().get(0).getPcount();
                    viewModel.bookPageList.observe(ProductActivity.this, new Observer<PagedList<Products.ProductsData>>() {
                        @Override
                        public void onChanged(PagedList<Products.ProductsData> books) {
                            shimmerFrameLayout.stopShimmer();
                            shimmerFrameLayout.setVisibility(View.GONE);
                            productAdapter.submitList(books);
                        }
                    });
                }
                else
                {
                    Snackbar.make(findViewById(android.R.id.content),"Network Error",Snackbar.LENGTH_LONG);
                }
            }
        });

    }

    @Override

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                onBackPressed();
                return true;

        }

        return super.onOptionsItemSelected(item);
    }

}