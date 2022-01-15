package org.example;
import com.github.javafaker.Faker;
import lombok.SneakyThrows;
import okhttp3.ResponseBody;
import org.hamcrest.*;
import org.junit.jupiter.api.*;
import retrofit2.Response;


import org.example.util.RetrofitUtils;

import static org.hamcrest.MatcherAssert.assertThat;

import org.example.dto.Product;
import org.example.service.ProductService;

import org.junit.jupiter.api.BeforeAll;

public class CreateProductTest {
    static ProductService productService;
    Product product;
    Faker faker = new Faker();
    int id;
    int currentId;

    @BeforeAll
    static void beforeAll(){
        productService = RetrofitUtils.getRetrofit()
                .create(ProductService.class);
    }
    @BeforeEach
    void setUp(){
        product = new Product()
                .withTitle(faker.food().ingredient())
                .withCategoryTitle("Food")
                .withPrice((int) (Math.random() * 10000));
    }
    @Test
    @SneakyThrows
    void createProductInFoodCategoryTest(){
        Response<Product> response = productService.createProduct(product)
                .execute();
        id= response.body().getId();
        assertThat(response.isSuccessful(), CoreMatchers.is(true));
    }
    @Test
    @SneakyThrows
    void getProdTest(){
        currentId = productService.createProduct(product).execute().body().getId();
        Response<Product> response = productService.getProduct(currentId)
                .execute();
        id=currentId;
        assertThat(response.isSuccessful(), CoreMatchers.is(true));
    }
    @Test
    @SneakyThrows
    void putProductTest(){
        currentId = productService.createProduct(product).execute().body().getId();
        System.out.println(currentId);

        Product newProduct = new Product()
                .withId(currentId)
                .withTitle("Bread")
                .withPrice((int) 500)
                .withCategoryTitle("Food");
        System.out.println(newProduct);
        Response<Product> response = productService.modifyProduct(newProduct).execute();
        System.out.println(response);
        id=currentId;
        assertThat(response.isSuccessful(),CoreMatchers.is(true));

    }


    @SneakyThrows
    @AfterEach
    void tearDown(){
        Response<ResponseBody> response = productService.deleteProduct(id).execute();
        assertThat(response.isSuccessful(), CoreMatchers.is(true));
    }
}
