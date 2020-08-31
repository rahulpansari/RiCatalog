package com.ricatalog.ricatalog.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.ricatalog.ricatalog.network.ProductCount;
import com.ricatalog.ricatalog.network.Products;
import com.ricatalog.ricatalog.network.SubCategory;
import com.ricatalog.ricatalog.pagination.ProductDataSource;
import com.ricatalog.ricatalog.pagination.ProductDataSourceFactory;
import com.ricatalog.ricatalog.repository.ProductActivityRepository;
import com.ricatalog.ricatalog.repository.SubCategoryRepository;

public class ProductActivityViewModel extends ViewModel {
    ProductActivityRepository productActivityRepository;
    public LiveData<PagedList<Products.ProductsData>> bookPageList;
    public LiveData<ProductDataSource> liveDataSource;

    public ProductActivityViewModel() {
        super();
        productActivityRepository=productActivityRepository.getInstance();
        ProductDataSourceFactory factory=new ProductDataSourceFactory();
        liveDataSource=factory.getBooklivedatasource();

        PagedList.Config config=(new PagedList.Config.Builder()).setEnablePlaceholders(false).setPageSize(10).build();


        bookPageList=new LivePagedListBuilder(factory,config).build();


    }
    public MutableLiveData<ProductCount> getProductCount()
    {
        return  productActivityRepository.getProductCountNo();
    }


    public void getProductsCountNo(String sid)
    {
        productActivityRepository.fetchTotalProducts(sid);
    }

}
