package com.devsuperior.dscatalog.dto;

import com.devsuperior.dscatalog.entity.Category;
import com.devsuperior.dscatalog.entity.Product;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ProductDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private String description;
    private Double price;
    private String imgUrl;
    private Instant date;

   private List<CategoryDTO> categoriesDTO = new ArrayList<>();

   public ProductDTO(){

   }

    public ProductDTO(Long id, String name, String description, Double price, String imgUrl, Instant date) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imgUrl = imgUrl;
        this.date = date;
    }

    public ProductDTO(Product entity){
       this.id = entity.getId();
       this.name = entity.getName();
       this.description = entity.getDescription();
       this.price = entity.getPrice();
       this.imgUrl = entity.getImgUrl();
       this.date = entity.getDate();
    }

    public ProductDTO(Product entity, Set<Category> categories){
       this(entity);
       categories.forEach(x -> this.categoriesDTO.add(new CategoryDTO(x)));
    }


}
