package com.ricatalog.ricatalog.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ricatalog.ricatalog.R;
import com.ricatalog.ricatalog.activity.MainActivity;
import com.ricatalog.ricatalog.database.CartEntity;
import com.ricatalog.ricatalog.utils.CartItemListener;
import com.ricatalog.ricatalog.utils.Utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CartAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<CartEntity> cartEntityList;
    CartItemListener listener;
    Context context;
    public CartAdapter(Context c,CartItemListener l)
    {
        context=c;
        listener=l;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType==0)
        {   View v=LayoutInflater.from(context).inflate(R.layout.cart_recycler_layout,parent,false);
            return new CartHolder(v);
        }
        else
        {View v=LayoutInflater.from(context).inflate(R.layout.button_proceed,parent,false);
            return new ButtonHolder(v);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if(position<cartEntityList.size())
        return 0;
        else
            return 1;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(position<cartEntityList.size())
        {
            ( (CartHolder)holder).title.setText(cartEntityList.get(position).getTitle());
            ((CartHolder)holder).color.setText(cartEntityList.get(position).getColor());
            ((CartHolder)holder).textqty.setText(cartEntityList.get(position).getQtyorder());
            ((CartHolder)holder).code.setText(cartEntityList.get(position).getCode());
            ((CartHolder)holder).polish.setText(cartEntityList.get(position).getPolish());
            Utils.setNewImage(( (CartHolder)holder).cartimage,context,cartEntityList.get(position).getImgurl());
            ( (CartHolder)holder).decreaseqty.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.decreaseQty(cartEntityList.get(position));

                }
            });
            ( (CartHolder)holder).increaseqty.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.increaseQty(cartEntityList.get(position));
                }
            });

            ( (CartHolder)holder).deleteitem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.deleteItem(cartEntityList.get(position));
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return cartEntityList!=null?cartEntityList.size()+1:0;
    }
public void setAdapter(List<CartEntity> cartEntities)
{
    cartEntityList=cartEntities;
    notifyDataSetChanged();
}
    public class CartHolder extends RecyclerView.ViewHolder
    {   @BindView(R.id.title)
        TextView title;
        @BindView(R.id.code)
        TextView code;
        @BindView(R.id.decreaseqty)
        TextView decreaseqty;
        @BindView(R.id.increaseqty)
        TextView increaseqty;
        @BindView(R.id.textqty)
        TextView textqty;
        @BindView(R.id.color)
        TextView color;
        @BindView(R.id.polish)
        TextView polish;
        @BindView(R.id.deleteitem)
        ImageView deleteitem;
        @BindView(R.id.cartimage)
        ImageView cartimage;
        public CartHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
    public class ButtonHolder extends RecyclerView.ViewHolder
    {@BindView(R.id.loginbttn)
    Button proceed;
    @BindView(R.id.submitbbtn)
    Button submitcart;
    @BindView(R.id.submitprogress)
    ProgressBar submitprogress;
    @BindView(R.id.loginprogress)
        ProgressBar progressBar;
        public ButtonHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
        @OnClick(R.id.loginbttn)
        public void moveMainActivity(View v)
        {
            ((Activity)context).finish();
        }
        @OnClick(R.id.submitbbtn)
        public void checkoutCart(View v)
        {   //submitcart.setVisibility(View.GONE);
        //submitprogress.setVisibility(View.VISIBLE);
            Map<String,String> map=new HashMap<>();
            String pid=cartEntityList.get(0).getId();
            String code=cartEntityList.get(0).getCode();
            String qty=cartEntityList.get(0).getQtyorder();
            String price=cartEntityList.get(0).getTitle();
            String polish=cartEntityList.get(0).getPolish();
            String color=cartEntityList.get(0).getColor();
            String id=Utils.getShraredPreferences(context).getString("id",null);
            for(int i=1;i<cartEntityList.size();i++)
            {
                pid=pid+","+cartEntityList.get(i).getId();
                code=code+","+cartEntityList.get(i).getCode();
                qty=qty+","+cartEntityList.get(i).getQtyorder();
                price=price+","+cartEntityList.get(i).getTitle();
                polish=polish+","+cartEntityList.get(i).getPolish();
                color=color+","+cartEntityList.get(i).getColor();
            }
            map.put("pid",pid);
            map.put("code",code);
            map.put("qty",qty);
            map.put("price",price);
            map.put("polish",polish);
            map.put("color",color);
            map.put("id",id);
                        listener.emptyCart(map);
        }
    }
}
