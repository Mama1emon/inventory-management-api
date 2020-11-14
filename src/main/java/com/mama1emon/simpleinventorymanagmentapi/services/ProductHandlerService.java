package com.mama1emon.simpleinventorymanagmentapi.services;

import com.mama1emon.simpleinventorymanagmentapi.dto.ProductDTO;
import com.mama1emon.simpleinventorymanagmentapi.models.Product;
import com.mama1emon.simpleinventorymanagmentapi.repositories.ProductRepository;
import com.mama1emon.simpleinventorymanagmentapi.util.ProductConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductHandlerService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> findProductByNameAndBrand(String name, String brand){
        List<Product> products = new ArrayList<>();
        if(name == null && brand == null)   return null;
        if(name == null)    return productRepository.findProductsByBrand(brand);
        if(brand == null)   return productRepository.findProductsByName(name);
        // Проверка на пустой массив
        if((products = productRepository.findProductsByNameAndBrand(name, brand)).isEmpty())
            return null;
        return products;
    }

    public void saveProduct(ProductDTO productDTO){
        productRepository.save(ProductConverter.convert(productDTO));
    }

    public void deleteProduct(Product product){
        productRepository.delete(product);
    }

    public List<Product> findProductLeftovers(){
        return productRepository.findProductByQuantityLessThan(5L);
    }
}
