package com.devsuperior.dscatalog.controllers;


import com.devsuperior.dscatalog.dto.ProductDTO;
import com.devsuperior.dscatalog.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping()
    public ResponseEntity<List<ProductDTO>> findAll(){
        List<ProductDTO> productsDTO = service.findAll();
        return ResponseEntity.ok(productsDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> findById(@PathVariable Long id){
        ProductDTO productDTO = service.findById(id);
        return ResponseEntity.ok(productDTO);
    }

    @PostMapping()
    public ResponseEntity<ProductDTO> save(@RequestBody ProductDTO dto){
        ProductDTO productDTO = service.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(productDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> update(@RequestBody ProductDTO dto, @PathVariable Long id){
        ProductDTO productDTO = service.update(dto, id);
        return ResponseEntity.ok(productDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
