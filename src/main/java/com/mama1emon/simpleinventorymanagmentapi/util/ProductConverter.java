package com.mama1emon.simpleinventorymanagmentapi.util;

import com.mama1emon.simpleinventorymanagmentapi.dto.ProductDTO;
import com.mama1emon.simpleinventorymanagmentapi.models.Product;

public class ProductConverter {
    // From ProductDTO
    public static Product convert(ProductDTO productDTO){
        return new Product().builder()
                .name(productDTO.getName())
                .brand(productDTO.getBrand())
                .price(Long.parseLong(productDTO.getPrice()))
                .quantity(Long.parseLong(productDTO.getQuantity()))
                .build();
    }
}
