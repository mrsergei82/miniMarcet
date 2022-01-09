package org.example;

import com.github.javafaker.Faker;
import lombok.SneakyThrows;
import org.example.dto.GetCategoryResponse;
import org.example.dto.GetProductResponse;
import org.example.dto.Product;
import org.example.service.ProductService;
import org.example.util.RetrofitUtils;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import retrofit2.Response;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class GetProductTest {
    static ProductService productService;
    Product product;
    Faker faker = new Faker();
    int id;
    @BeforeAll
    static void beforeAll() {
        productService = RetrofitUtils.getRetrofit()
                .create(ProductService.class);
    }


    @Test
    @SneakyThrows
    void getProduct() {
        Response<Product> response = productService.getProduct(20442)
                .execute();
        assertThat(response.isSuccessful(), CoreMatchers.is(true));

        assertThat(response.body().getCategoryTitle(), equalTo("Food"));
    }

}
