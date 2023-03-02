package com.javaproject.Ecommerce.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class GetProductList {
    List<ListOfProductsDto> listOfProducts;
}
