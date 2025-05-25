package com.yoi.application.model;

/*
 * @author Yoi
 * @date 2025/04/17
 * @description Product class representing a product entity.
 */

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class ProductDto {
    private Long id;
    @NotBlank(message = "El nombre es obligatorio")
    private String name;
    @Min(value = 1, message = "El precio debe ser mayor que 0")
    private Double price;

    public ProductDto(){}

    public ProductDto(Long id, String name, Double price){
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Long getId(){ return id; }

    public void setId(Long id){ this.id = id; }

    public String getName(){ return name; }

    public void setName(String name){ this.name = name;}

    public Double getPrice(){ return price;}

    public void setPrice(Double price){ this.price = price;}
}
