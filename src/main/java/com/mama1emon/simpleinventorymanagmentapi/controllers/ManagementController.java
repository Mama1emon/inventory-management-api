package com.mama1emon.simpleinventorymanagmentapi.controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mama1emon.simpleinventorymanagmentapi.dto.ProductDTO;
import com.mama1emon.simpleinventorymanagmentapi.models.Product;
import com.mama1emon.simpleinventorymanagmentapi.services.ProductHandlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.websocket.server.PathParam;

@RestController
public class ManagementController {
    @Autowired
    private ProductHandlerService productHandler;

    private Gson gson = new GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create();

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

    @GetMapping("/get")
    public String get(@PathParam("name") String name,
                      @PathParam("brand") String brand){
        return gson.toJson(productHandler.findProductByNameAndBrand(name, brand));
    }
}

