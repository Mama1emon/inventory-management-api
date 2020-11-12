package com.mama1emon.simpleinventorymanagmentapi.dto;

/*
 * Объединение классов для упрощения кода(полиморфизм)
 */
public interface ProductDTO {
    String getName();
    String getBrand();
    String getPrice();
    String getQuantity();
}
