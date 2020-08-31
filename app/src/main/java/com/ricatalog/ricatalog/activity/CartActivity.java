package com.ricatalog.ricatalog.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.ricatalog.ricatalog.R;
import com.ricatalog.ricatalog.adapter.CartAdapter;
import com.ricatalog.ricatalog.database.CartEntity;
import com.ricatalog.ricatalog.network.Checkout;
import com.ricatalog.ricatalog.utils.CartItemListener;
import com.ricatalog.ricatalog.utils.Utils;
import com.ricatalog.ricatalog.viewmodel.CartViewModel;

import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CartActivity extends AppCompatActivity implements CartItemListener {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.cartrecycler)
    RecyclerView cartrecycler;
    @BindView(R.id.noitem)
    TextView noitem;
    CartViewModel cartviewmodel;
    CartAdapter cartAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        ButterKnife.bind(this);
        toolbar.setTitle("Cart Details");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        cartAdapter=new CartAdapter(CartActivity.this,this);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        cartrecycler.setLayoutManager(layoutManager);
        cartrecycler.setAdapter(cartAdapter);
        toolbar.setTitleTextColor(getResources().getColor(R.color.whitecolor));
        cartviewmodel= new ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(CartViewModel.class);
        cartviewmodel.getEntity().observe(this, new Observer<List<CartEntity>>() {
            @Override
            public void onChanged(List<CartEntity> cartEntities) {
                if (cartEntities != null) {
                    cartAdapter.setAdapter(cartEntities);
                    if(cartEntities.size()==0)
                    {
                        cartrecycler.setVisibility(View.GONE);
                        noitem.setVisibility(View.VISIBLE);
                    }
                    else
                    {
                        noitem.setVisibility(View.GONE);
                        cartrecycler.setVisibility(View.VISIBLE);
                    }
                }
            }
        });
cartviewmodel.getCheckoutLiveData().observe(this, new Observer<Checkout>() {
    @Override
    public void onChanged(Checkout checkout) {
        if(checkout!=null)
        {
            Utils.showSuccesfulToast(CartActivity.this,CartActivity.this,"Your request has been succesfully recieved");
            cartviewmodel.removeAll();
        }
        else
        {
            Utils.showErrorToast(CartActivity.this,CartActivity.this,"Something Went Wrong");
        }
    }
});
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void increaseQty(CartEntity entity) {
        int maxlimit=Integer.parseInt(entity.getQtyinstock());
        int qty=Integer.parseInt(entity.getQtyorder());
        if(qty==maxlimit)
        {
            Utils.showErrorToast(CartActivity.this,CartActivity.this,"Item Cant be Greater than Max Quantity");
        }
        else
        {   qty++;
            entity.setQtyorder(String.valueOf(qty));
            cartviewmodel.updateProduct(entity);
        }
    }

    @Override
    public void decreaseQty(CartEntity entity) {
        int qty=Integer.parseInt(entity.getQtyorder());
        if(qty==1)
        {
            Utils.showErrorToast(CartActivity.this,CartActivity.this,"Item Cant be Less than 1");
        }
        else
        {   qty--;
            entity.setQtyorder(String.valueOf(qty));
            cartviewmodel.updateProduct(entity);
        }
    }

    @Override
    public void deleteItem(CartEntity entity) {
        cartviewmodel.removeProduct(entity);
    }

    @Override
    public void emptyCart(Map<String,String> map) {
        cartviewmodel.fetchCheckout(map);
    }
}