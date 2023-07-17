package com.example.devApplication.service;

import com.example.devApplication.entity.Product;
import com.example.devApplication.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<Product> getAllProducts(){return productRepository.findAll();}

    public Product createProduct(Product product){
        return productRepository.save(product);
    }

    public List<Product> searchProduct(String param){
        return productRepository.searchProduct(param);
    }

    public Product getProduct(Long id){
        return productRepository.findById(id).get();
    }

}
