package com.yoi.application.Model;

import java.time.LocalDate;

/*
 * @author Yoi
 * @date 2025/04/17
 * @description Deliver class representing a delivery entity.
 */

public class Deliver {
    private Long id;
    private User user;
    private Product product;
    private LocalDate date;

    public Deliver(){}

    public Deliver(Long id, User user, Product product, LocalDate date){
        this.id = id;
        this.user = user;
        this.product = product;
        this.date = date;
    }

    public Long getId(){ return id; }

    public void setId(Long id){ this.id = id; }

    public User getUser(){ return user; }

    public void setUser(User user){ this.user = user; }

    public Product getProduct(){ return product; }

    public void setProduct(Product product) { this.product = product; }

    public LocalDate getDate(){ return date; }

    public void setDate(LocalDate date){ this.date = date; }

}
