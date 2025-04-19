package com.yoi.application.Persistence.DAO;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    @ManyToMany
    @JoinTable(name = "DELIVER_PRODUCT",
            joinColumns = @JoinColumn(name = "DELIVER_ID"),
            inverseJoinColumns = @JoinColumn(name = "PRODUCT_ID"))
    private  List<ProductDAO> product = new ArrayList<>();

    @Column(name = "TOTAL", nullable = false)
    private Double total = 0.0;

    @Column(name = "TAXES", nullable = false)
    private Double taxes = 0.0;

    @Column(name = "DISCOUNT", nullable = false)
    private Double discount = 0.0;

    @NotBlank
    @Column(name = "DATE")
    private LocalDate date;

    public DeliverDAO(){}

    public DeliverDAO(Long id, UserDAO user, List<ProductDAO> product,
                      Double total, Double taxes, Double discount, LocalDate date){
        this.id = id;
        this.user = user;
        this.product = product;
        this.total = total;
        this.taxes = taxes;
        this.discount = discount;
        this.date = date;
    }

    public Long getId(){ return id; }

    public void setId(Long id){ this.id = id; }

    public UserDAO getUser(){ return user; }

    public void setUser(UserDAO user){ this.user = user; }

    public List<ProductDAO> getProduct(){ return product; }

    public void setProduct(List<ProductDAO> product) { this.product = product; }

    public Double getTotal(){ return total; }

    public void setTotal(Double total){ this.total = total; }

    public Double getTaxes(){ return taxes; }

    public void setTaxes(Double taxes){ this.taxes = taxes; }

    public Double getDiscount(){ return discount; }

    public void setDiscount(Double discount){ this.discount = discount; }

    public LocalDate getDate(){ return date; }

    public void setDate(LocalDate date){ this.date = date; }
}
