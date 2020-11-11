package com.mama1emon.simpleinventorymanagmentapi.controllers;

import com.mama1emon.simpleinventorymanagmentapi.dto.ProductDTO;
import com.mama1emon.simpleinventorymanagmentapi.models.Product;
import com.mama1emon.simpleinventorymanagmentapi.services.ProductHandlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
public class ManagementController {
    @Autowired
    private ProductHandlerService productHandler;

    @PostMapping("/add")
    public void addProduct(@Valid @RequestBody ProductDTO productDTO, HttpServletResponse response){
        // Если продукт уже существует
        if(productHandler.findProductByNameAndBrand(productDTO.getName(), productDTO.getBrand()) != null){
            response.setStatus(HttpServletResponse.SC_BAD_GATEWAY);
            return;
        }
        productHandler.saveProduct(new Product().builder()
                .name(productDTO.getName())
                .brand(productDTO.getBrand())
                .price(Long.parseLong(productDTO.getPrice()))
                .quantity(Long.parseLong(productDTO.getQuantity()))
                .build());
        response.setStatus(HttpServletResponse.SC_OK);
    }
}

