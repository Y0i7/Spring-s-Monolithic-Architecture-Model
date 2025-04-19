package com.yoi.application.Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/*
 * @author Yoi
 * @date 2025/04/17
 * @description Deliver class representing a delivery entity.
 */

public class Deliver {
    private Long id;
    private User user;
    private List<Product> products = new ArrayList<>();
    private Double total;
    private Double taxes;
    private Double discount;
    private LocalDate date;


    public Deliver(){}

    public Deliver(Long id, User user, List<Product> products, Double total,
            Double taxes, Double discount,LocalDate date){
        this.id = id;
        this.user = user;
        this.products = products;
        this.total = total;
        this.taxes = taxes;
        this.discount = discount;
        this.date = date;
    }

    public Long getId(){ return id; }

    public void setId(Long id){ this.id = id; }

    public User getUser(){ return user; }

    public void setUser(User user){ this.user = user; }

    public List<Product> getProduct(){ return products; }

    public void setProduct(List<Product> products) { this.products = products; }

    public void setTotal(Double total){ this.total = total; }

    public Double getTotal(){ return total; }

    public void setTaxes(Double taxes){ this.taxes = taxes; }

    public Double getTaxes(){ return taxes; }

    public void setDiscount(Double discount){ this.discount = discount; }

    public Double getDiscount(){ return discount; }

    public LocalDate getDate(){ return date; }

    public void setDate(LocalDate date){ this.date = date; }

}
