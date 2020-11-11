package com.mama1emon.simpleinventorymanagmentapi.services;

import com.mama1emon.simpleinventorymanagmentapi.models.Product;
import com.mama1emon.simpleinventorymanagmentapi.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductHandlerService {
    @Autowired
    private ProductRepository productRepository;

    public Product findProductByNameAndBrand(String name, String brand){
        return productRepository.findProductByNameAndBrand(name, brand);
    }

    public void saveProduct(Product product){
        productRepository.save(product);
    }
}
