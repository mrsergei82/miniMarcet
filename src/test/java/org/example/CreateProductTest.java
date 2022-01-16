package org.example;
import com.github.javafaker.Faker;
import lombok.SneakyThrows;
import okhttp3.ResponseBody;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.hamcrest.*;
import org.junit.jupiter.api.*;
import retrofit2.Response;
import org.example.util.RetrofitUtils;
import static org.hamcrest.MatcherAssert.assertThat;
import org.example.dto.Product;
import org.example.service.ProductService;
import org.junit.jupiter.api.BeforeAll;
import ru.geekbrains.java4.lesson6.db.dao.ProductsMapper;

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
        Response<Product> response = productService.getProduct((int) currentId)
                .execute();
        id=currentId;
        assertThat(response.isSuccessful(), CoreMatchers.is(true));
    }
    @Test
    @SneakyThrows
    void putProductTest() throws Exception{
        currentId = productService.createProduct(product).execute().body().getId();
        Product newProduct = new Product()
                .withId((int)currentId)
                .withTitle("Bread")
                .withPrice((int) 500)
                .withCategoryTitle("Food");
        Response<Product> response = productService.modifyProduct(newProduct).execute();
        id=currentId;
        assertThat(response.isSuccessful(),CoreMatchers.is(true));
        ProductsMapper pm = Main.getProductMaper();
        pm.selectByPrimaryKey((long)response.body().getId()).equals(currentId);
    }
    @SneakyThrows
    @AfterEach
    void tearDown(){
        Response<ResponseBody> response = productService.deleteProduct((int) id).execute();
        assertThat(response.isSuccessful(), CoreMatchers.is(true));
    }
}
