package com.mama1emon.simpleinventorymanagmentapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ProductDTO {
    @NotBlank(message = "Name isn't mandatory")
    private String name;
    @NotBlank(message = "Brand isn't mandatory")
    private String brand;
    @NotBlank(message = "Price isn't mandatory")
    private String price;
    @NotBlank(message = "Quantity isn't mandatory")
    private String quantity;
}
