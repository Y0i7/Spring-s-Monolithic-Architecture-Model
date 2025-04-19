package com.yoi.application.Persistence.DAO;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/*
 * @author Yoi
 * @date 2025/04/17
 * @description ProductDAO class representing the product entity in the database.
 */
@Entity
@Table(name = "PRODUCTS")
public class ProductDAO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @NotBlank
    @Size(min = 3, message = "name should be at least 3 characters")
    @Column(name = "NAME")
    private String name;

    @NotBlank
    @Column(name = "PRICE")
    private Double price;

    public ProductDAO() {}

    public ProductDAO(Long id, String name, Double price){
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
