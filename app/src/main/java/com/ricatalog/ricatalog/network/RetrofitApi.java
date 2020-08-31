package com.ricatalog.ricatalog.network;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitApi {
    public static String URL="http://radiantimpexjewels.com/erp/app/";
    @GET("login.php")
    Call<Login> loginUser(@Query("mobile")String mobile,@Query("password")String password);

    @GET("insert.php")
    Call<Registeration> registerUser(@Query("name")String name,@Query("mobile")String mobile,@Query("email")String email,@Query("place")String place,@Query("company_name")String company_name);

    @GET("category.php")
    Call<Category> getCategories();

    @GET("subcategory.php")
    Call<SubCategory> getSubCategories(@Query("catid")String catid);

    @GET("productcount.php")
    Call<ProductCount> getProductCount(@Query("subcatid")String id);

    @GET("edit_profile.php")
    Call<EditProfile> getProfile(@Query("id")String id);

    @GET("product_new.php")
    Call<Products> getProducts(@Query("subcatid")String id,@Query("limit1")String limit1,@Query("limit2")String limit2);

    @GET("product_detail.php")
    Call<ProductDetail>getProductDetails(@Query("id")String id);

    @GET("update_profile.php")
    Call<ProfileUpdate>updateProfileDetails(@Query("id")String id,@Query("country")String country,@Query("password")String passowrd,@Query("m_phone")String phone,@Query("state")String state,@Query("city")String city,@Query("code")String code,@Query("address")String address,@Query("company_name")String company_name,@Query("username")String username,@Query("email")String email,@Query("mobile")String mobileno,@Query("name")String name,@Query("gst_no")String gst);

    @GET("country.php")
    Call<ProfileCountry>getCountryList();

    @GET("checkout.php")
    Call<Checkout>checkoutCart(@Query("pid")String pid,@Query("code")String code,@Query("qty")String qty,@Query("price")String price,@Query("polish")String polish,@Query("color")String color,@Query("userid")String id);
    
}
