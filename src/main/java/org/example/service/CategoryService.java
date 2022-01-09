package org.example.service;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import org.example.dto.GetCategoryResponse;
public interface CategoryService {
   @GET("categories/{id}")
   Call<GetCategoryResponse> getCategory(@Path("id") int id);
   @GET("categories/notresult/{id}")
   Call<ResponseBody> getCategoryWithoutResult(@Path("id") int id);
}
