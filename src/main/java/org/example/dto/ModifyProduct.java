package org.example.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ModifyProduct {

    @JsonProperty("price")
    private Integer price;


    public ModifyProduct withPrice(Integer price) {
        this.price = price;
        return this;
    }



}
