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

import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.snackbar.Snackbar;
import com.ricatalog.ricatalog.R;
import com.ricatalog.ricatalog.adapter.SubCategroyAdapter;
import com.ricatalog.ricatalog.network.SubCategory;
import com.ricatalog.ricatalog.viewmodel.MainActivityViewModel;
import com.ricatalog.ricatalog.viewmodel.SubCategoryViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SubCategoryActivity extends AppCompatActivity {
@BindView(R.id.subcategoryrecycler)
RecyclerView subcategoryrecycler;
@BindView(R.id.toolbar)
Toolbar toolbar;
@BindView(R.id.shimmer_view_container)
ShimmerFrameLayout shimmerFrameLayout;
SubCategoryViewModel viewModel;
SubCategroyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_category);
        ButterKnife.bind(this);
        String category=getIntent().getStringExtra("category");
        toolbar.setTitle(category);
        setSupportActionBar(toolbar);
        shimmerFrameLayout.startShimmer();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setTitleTextColor(getResources().getColor(R.color.whitecolor));
        viewModel=new ViewModelProvider(this).get(SubCategoryViewModel.class);
        viewModel.getSubCategories(getIntent().getStringExtra("catid"));
        adapter=new SubCategroyAdapter(SubCategoryActivity.this);
        viewModel.getSubCategoryList().observe(this, new Observer<SubCategory>() {
            @Override
            public void onChanged(SubCategory subCategory) {
                if(subCategory!=null) {
                    shimmerFrameLayout.stopShimmer();
                    shimmerFrameLayout.setVisibility(View.GONE);
                    adapter.setAdapter(subCategory.getSubcategorylist());
                }
                else
                {
                    Snackbar.make(findViewById(android.R.id.content),"Network Error",Snackbar.LENGTH_LONG);
                }
            }
        });
        LinearLayoutManager manager=new LinearLayoutManager(getApplicationContext(),RecyclerView.VERTICAL,false);
        subcategoryrecycler.setLayoutManager(manager);
        subcategoryrecycler.setAdapter(adapter);


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