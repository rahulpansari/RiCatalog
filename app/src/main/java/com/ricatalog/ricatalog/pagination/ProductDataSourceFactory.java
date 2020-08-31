package com.ricatalog.ricatalog.pagination;


import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

public class ProductDataSourceFactory extends DataSource.Factory {
    private MutableLiveData<ProductDataSource> booklivedatasource=new MutableLiveData<>();
    public ProductDataSource bookDataSource;
    @NonNull
    @Override
    public DataSource create() {
        bookDataSource=new ProductDataSource();
        booklivedatasource.postValue(bookDataSource);
        return bookDataSource;
    }

    public MutableLiveData<ProductDataSource> getBooklivedatasource()
    {
        return booklivedatasource;
    }


}
