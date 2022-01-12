package org.example.service;

import okhttp3.ResponseBody;
import org.example.dto.Product;
import retrofit2.Call;
import retrofit2.http.*;

public interface  ProductService {
    @POST("products")
    Call<Product> createProduct(@Body Product createProductRequest);
    @GET("products/{id}")
    Call<Product> getProduct(@Path("id") int id);
    @PUT("products/{id}")
    Call<Product> modifyProduct(@Body Product modifyProductRequest);

    @DELETE("products/{id}")
    Call<ResponseBody> deleteProduct(@Path("id") int id);
}
