package com.ricatalog.ricatalog.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ricatalog.ricatalog.R;
import com.ricatalog.ricatalog.activity.ProductActivity;
import com.ricatalog.ricatalog.network.Category;
import com.ricatalog.ricatalog.network.SubCategory;
import com.ricatalog.ricatalog.utils.Utils;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

public class SubCategroyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<SubCategory.SubCategoryList> categoryLists;
    Context context;
    public SubCategroyAdapter(Context c) {
        super();
        context=c;


    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.main_category_adapter,parent,false);
        return new SubCategoryHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Utils.setImage(((SubCategoryHolder) holder).image,context,categoryLists.get(position).getImgpath());
        ((SubCategoryHolder)holder).itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(context, ProductActivity.class);
                i.putExtra("sid",categoryLists.get(position).getSubcatid());
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryLists!=null?categoryLists.size():0;
    }

    public void setAdapter(List<SubCategory.SubCategoryList> categoryListList)
    {
        categoryLists=categoryListList;
        notifyDataSetChanged();
    }
    public class SubCategoryHolder extends RecyclerView.ViewHolder
    {   @BindView(R.id.categoryimg)
    ImageView image;
        public SubCategoryHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
