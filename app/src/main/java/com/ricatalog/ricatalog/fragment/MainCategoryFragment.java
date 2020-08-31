package com.ricatalog.ricatalog.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ricatalog.ricatalog.R;
import com.ricatalog.ricatalog.adapter.CategoryAdapter;
import com.ricatalog.ricatalog.network.Category;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainCategoryFragment extends Fragment {
    @BindView(R.id.categoryrecycler)
    RecyclerView categoryrecycler;
    CategoryAdapter categoryAdapter;
    ArrayList<Category.CategoryList> categoryListList;
    public static MainCategoryFragment getInstance()
    {
        return  new MainCategoryFragment();
    }
    public MainCategoryFragment() {
        super();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View v=inflater.inflate(R.layout.category_fragment,container,false);
       Bundle bundle=getArguments();
       categoryListList=(ArrayList<Category.CategoryList>)((Category)bundle.getParcelable("list")).getCategoryList();
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        categoryAdapter=new CategoryAdapter(getContext());
        categoryrecycler.setLayoutManager(layoutManager);
        categoryrecycler.setAdapter(categoryAdapter);
        categoryAdapter.setAdapter(categoryListList);


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        }
}
