package org.example.dto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
@Data
public class Product {
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("title")
    private String title;
    @JsonProperty("price")
    private Integer price;
    @JsonProperty("categoryTitle")
    private String categoryTitle;
    public Product withId(Integer id) {
        this.id = id;
        return this;
    }

    public Product withTitle(String title) {
        this.title = title;
        return this;
    }

    public Product withPrice(Integer price) {
        this.price = price;
        return this;
    }

    public Product withCategoryTitle(String categoryTitle) {
        this.categoryTitle = categoryTitle;
        return this;
    }

}
