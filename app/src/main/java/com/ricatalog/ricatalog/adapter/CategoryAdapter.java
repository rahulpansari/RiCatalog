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
import com.ricatalog.ricatalog.activity.SubCategoryActivity;
import com.ricatalog.ricatalog.network.Category;
import com.ricatalog.ricatalog.network.SubCategory;
import com.ricatalog.ricatalog.utils.Utils;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<Category.CategoryList> categoryLists;
    Context context;
    public CategoryAdapter(Context c) {
        super();
        context=c;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.main_category_adapter,parent,false);
        return new CategoryHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Utils.setImage(((CategoryHolder) holder).image,context,categoryLists.get(position).getImgpath());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(context, SubCategoryActivity.class);
                i.putExtra("catid",categoryLists.get(position).getCatid());
                i.putExtra("category",categoryLists.get(position).getCategory());
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryLists!=null?categoryLists.size():0;
    }

    public void setAdapter(List<Category.CategoryList> categoryListList)
    {
        categoryLists=categoryListList;
        notifyDataSetChanged();
    }
    public class CategoryHolder extends RecyclerView.ViewHolder
    {   @BindView(R.id.categoryimg)
    ImageView image;
        public CategoryHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
