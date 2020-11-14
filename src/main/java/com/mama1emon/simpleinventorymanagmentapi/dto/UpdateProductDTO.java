package com.mama1emon.simpleinventorymanagmentapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
 * Для обновления продукта необходимы не все поля
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UpdateProductDTO implements ProductDTO {
    private String name;
    private String brand;
    private String price;
    private String quantity;
}