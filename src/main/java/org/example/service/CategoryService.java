package org.example.service;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import org.example.dto.GetCategoryResponse;
public interface CategoryService {
   @GET("cftegories/{id}")
   Call<GetCategoryResponse> getCategory(@Path("id") int id);
}
