package com.devsuperior.dscatalog.services;

import com.devsuperior.dscatalog.dto.CategoryDTO;
import com.devsuperior.dscatalog.dto.ProductDTO;
import com.devsuperior.dscatalog.entity.Category;
import com.devsuperior.dscatalog.entity.Product;
import com.devsuperior.dscatalog.repositories.CategoryRepository;
import com.devsuperior.dscatalog.repositories.ProductRepository;
import com.devsuperior.dscatalog.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    public List<ProductDTO> findAll() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(product -> new ProductDTO(product, product.getCategorySet()))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ProductDTO findById(Long id) {
       Optional<Product> product = productRepository.findById(id);
       Product productFound = product.orElseThrow(() -> new ResourceNotFoundException("Id not found."));
       return new ProductDTO(productFound, productFound.getCategorySet());
    }


    @Transactional
    public ProductDTO save(ProductDTO dto) {
        Product product = new Product();
        copyProductDtoToProduct(dto,product);
        product = productRepository.save(product);
        return new ProductDTO(product, product.getCategorySet());
    }

    @Transactional
    public ProductDTO update(ProductDTO dto, Long id) {
        try {
            Product product = productRepository.getReferenceById(id);
            copyProductDtoToProduct(dto, product);
            product = productRepository.save(product);
            return new ProductDTO(product, product.getCategorySet());
        }catch (EntityNotFoundException e){
            throw new ResourceNotFoundException("Id not found.");
        }

    }

    private void copyProductDtoToProduct(ProductDTO dto, Product product){
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setImgUrl(dto.getImgUrl());
        product.setDate(dto.getDate());

        product.getCategorySet().clear();

        for (CategoryDTO categoriesDTO : dto.getCategoriesDTO()){
            Category category = categoryRepository.getReferenceById(categoriesDTO.getId());
            product.getCategorySet().add(category);
        }

    }
}
