package com.mama1emon.simpleinventorymanagmentapi.controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mama1emon.simpleinventorymanagmentapi.dto.NewProductDTO;
import com.mama1emon.simpleinventorymanagmentapi.dto.UpdateProductDTO;
import com.mama1emon.simpleinventorymanagmentapi.models.Product;
import com.mama1emon.simpleinventorymanagmentapi.services.ProductHandlerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.io.IOException;
import java.util.List;

@RestController
public class ManagementController {
    @Autowired
    private ProductHandlerService productHandler;

    Logger logger = LoggerFactory.getLogger(ManagementController.class);
    private Gson gson = new GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create();

    @PostMapping("/add")
    public void addProduct(@Valid @RequestBody NewProductDTO productDTO, HttpServletResponse response){
        // Если продукт уже существует
        if(productHandler.findProductByNameAndBrand(productDTO.getName(), productDTO.getBrand()) != null){
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            logger.error(productDTO.getBrand() + " " + productDTO.getName() + "is already exist");
            return;
        }
        productHandler.saveProduct(productDTO);
        response.setStatus(HttpServletResponse.SC_CREATED);
        logger.info("Product created");
    }

    @GetMapping("/get")
    public String getProduct(@PathParam("name") String name,
                      @PathParam("brand") String brand){
        return gson.toJson(productHandler.findProductByNameAndBrand(name, brand));
    }

    @PostMapping("/update")
    public void updateProduct(@PathParam("name") String name,
                              @PathParam("brand") String brand,
                              @RequestBody UpdateProductDTO updateProduct,
                              HttpServletResponse response){
        // Проверка на корректность запроса
        if(name == null || brand == null){
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            logger.error(brand + name + "isn't found");
            return;
        }
        List<Product> products = productHandler.findProductByNameAndBrand(name, brand);

        // Проверка на существование продукта
        if(products == null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            logger.error(brand + name + "isn't found");
            return;
        }

        // Проблема на сервере: существует два одинаковых продукта
        if(products.size() > 1){
            response.setStatus(HttpServletResponse.SC_BAD_GATEWAY);
            logger.error("There are " + products.size() + " copies of the product");
            return;
        }
        productHandler.deleteProduct(products.get(0));
        productHandler.saveProduct(updateProduct);
        response.setStatus(HttpServletResponse.SC_ACCEPTED);
        logger.info("Product updated");
    }
}

