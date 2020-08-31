package com.ricatalog.ricatalog.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.ricatalog.ricatalog.R;
import com.ricatalog.ricatalog.activity.ProductDetail;
import com.ricatalog.ricatalog.activity.ZoomInActivity;
import com.ricatalog.ricatalog.network.Products;
import com.ricatalog.ricatalog.utils.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductAdapter extends PagedListAdapter<Products.ProductsData, ProductAdapter.ProductRecyclerHolder> {

    Context context;

    protected ProductAdapter(@NonNull DiffUtil.ItemCallback<Products.ProductsData> diffCallback) {
        super(diffCallback);
    }

    @Override
    public void registerAdapterDataObserver(@NonNull RecyclerView.AdapterDataObserver observer) {
        super.registerAdapterDataObserver(observer);
    }

    public ProductAdapter(Context c)
    {
        super(DIFF_CALLBACK);
        this.context=c;
    }
    @NonNull
    @Override
    public ProductRecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.cart_product,parent,false);
        return new ProductRecyclerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductRecyclerHolder holder, int position) {
        Products.ProductsData books=getItem(position);
        holder.code.setText("CODE:"+books.getCode());
        holder.pricecode.setText("PRICE CODE:"+books.getTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(context, ProductDetail.class);
                i.putExtra("id",books.getId());
                context.startActivity(i);
            }
        });
        holder.addcartbttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(context, ProductDetail.class);
                i.putExtra("id",books.getId());
                context.startActivity(i);
            }
        });
        Utils.setNewImage(holder.cardimg, context, books.getCimg());

    }
    private static DiffUtil.ItemCallback<Products.ProductsData> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Products.ProductsData>() {
                @Override
                public boolean areItemsTheSame(Products.ProductsData oldBookdata, Products.ProductsData newBookData) {
                    return oldBookdata.getId()==newBookData.getId();
                }

                @Override
                public boolean areContentsTheSame(Products.ProductsData oldBookData,
                                                  Products.ProductsData newBookData) {
                    return oldBookData.equals(newBookData);
                }
            };
    public class ProductRecyclerHolder extends RecyclerView.ViewHolder
    {   @BindView(R.id.cardimg)
    ImageView cardimg;
        @BindView(R.id.code)
        TextView code;
        @BindView(R.id.pricecode)
        TextView pricecode;
       /* @BindView(R.id.color)
        TextView color;
        @BindView(R.id.polish)
        TextView polish;
        @BindView(R.id.quantity)
        TextView quantity;
        @BindView(R.id.pricecode)
        TextView pricecode;*/
        @BindView(R.id.addcartbttn)
        Button addcartbttn;
        @BindView(R.id.frm)
        FrameLayout zoomBttn;

        public ProductRecyclerHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}