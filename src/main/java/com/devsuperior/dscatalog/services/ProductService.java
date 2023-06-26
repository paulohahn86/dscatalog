package com.devsuperior.dscatalog.services;

import com.devsuperior.dscatalog.dto.ProductDTO;
import com.devsuperior.dscatalog.entity.Product;
import com.devsuperior.dscatalog.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;


    @Transactional(readOnly = true)
    public List<ProductDTO> findAll() {
        List<Product> products = repository.findAll();
        return products.stream()
                .map(product -> new ProductDTO(product,product.getCategorySet()))
                .collect(Collectors.toList());
    }



}
