package com.yoi.application.Persistence.DAO;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

/*
 * @author Yoi
 * @date 2025/04/17
 * @description DeliverDAO class representing the deliver entity in the database.
 */

@Entity
@Table(name = "DELIVERS")
public class DeliverDAO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private UserDAO user;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID")
    private ProductDAO product;

    @NotBlank
    @Column(name = "DATE")
    private LocalDate date;

    public DeliverDAO(){}

    public DeliverDAO(Long id, UserDAO user, ProductDAO product, LocalDate date){
        this.id = id;
        this.user = user;
        this.product = product;
        this.date = date;
    }

    public Long getId(){ return id; }

    public void setId(Long id){ this.id = id; }

    public UserDAO getUser(){ return user; }

    public void setUser(UserDAO user){ this.user = user; }

    public ProductDAO getProduct(){ return product; }

    public void setProduct(ProductDAO product) { this.product = product; }

    public LocalDate getDate(){ return date; }

    public void setDate(LocalDate date){ this.date = date; }
}
