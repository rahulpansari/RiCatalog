package com.ricatalog.ricatalog.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ricatalog.ricatalog.network.ProductDetail;
import com.ricatalog.ricatalog.repository.ProductDetailRepository;

public class ProductDetailViewModel extends ViewModel {
    ProductDetailRepository detailRepository;
    private MutableLiveData<Integer> datapos;
    public ProductDetailViewModel() {
        super();
        detailRepository=ProductDetailRepository.getInstance();
        datapos=new MutableLiveData<>();
    }
  public MutableLiveData<ProductDetail>getList()
  {
      return detailRepository.getProductdetails();
  }

    public MutableLiveData<Integer> getDatapos() {
        return datapos;
    }

    public void getListDetails(String id)
  {
       detailRepository.fetchProductDetail(id);
  }
}
