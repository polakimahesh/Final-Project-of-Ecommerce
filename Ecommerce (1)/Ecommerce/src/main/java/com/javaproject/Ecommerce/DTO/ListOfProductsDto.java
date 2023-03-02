package com.javaproject.Ecommerce.DTO;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ListOfProductsDto {
    private  int productId;
    private String name;
    private int quantity;
    private double price;
    private  String description;

}
