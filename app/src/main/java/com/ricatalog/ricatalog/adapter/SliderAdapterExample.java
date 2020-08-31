package com.ricatalog.ricatalog.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.ricatalog.ricatalog.R;
import com.ricatalog.ricatalog.activity.ZoomInActivity;
import com.ricatalog.ricatalog.network.ProductDetail;
import com.ricatalog.ricatalog.utils.Utils;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.List;

public class SliderAdapterExample extends
        SliderViewAdapter<SliderAdapterExample.SliderAdapterVH> {
    private Activity context;
    private List<ProductDetail.ProductDetailList> mSliderItems ;
    public SliderAdapterExample(Activity context) {
        this.context = context;
    }

    @Override
    public SliderAdapterVH onCreateViewHolder(ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.slider_image, parent,false);
        return new SliderAdapterVH(inflate);
    }

    @Override
    public int getItemPosition(Object object) {
        return super.getItemPosition(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        return super.instantiateItem(container, position);
    }

    @Override
    public void onBindViewHolder(SliderAdapterVH viewHolder, final int position) {
        Utils.setNewImage(viewHolder.imageslide,context,mSliderItems.get(position).getImage());
        viewHolder.imageslide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(context, ZoomInActivity.class);
                i.putExtra("img",mSliderItems.get(position).getImage());
                context.startActivity(i);
            }
        });


    }

    @Override
    public int getCount() {
        return  mSliderItems!=null? mSliderItems.size():0;
    }

    public void setAdapter(List<ProductDetail.ProductDetailList> list)
    {
        mSliderItems=list;
        notifyDataSetChanged();
    }
    class SliderAdapterVH extends SliderViewAdapter.ViewHolder {

        View itemView;
        ImageView imageslide;
        public SliderAdapterVH(View itemView) {
            super(itemView);
            imageslide= itemView.findViewById(R.id.imageslide);
            this.itemView = itemView;
        }
    }

}