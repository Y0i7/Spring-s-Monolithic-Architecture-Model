package com.yoi.application.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/*
 * @author Yoi
 * @date 2025/04/17
 * @description Deliver class representing a delivery entity.
 */

public class DeliverDto {
    private Long id;
    private UserDto userDto;
    private List<ProductDto> productDtos = new ArrayList<>();
    private Double total;
    private Double taxes;
    private Double discount;
    private LocalDate date;


    public DeliverDto(){}

    public DeliverDto(Long id, UserDto userDto, List<ProductDto> productDtos, Double total,
                      Double taxes, Double discount, LocalDate date){
        this.id = id;
        this.userDto = userDto;
        this.productDtos = productDtos;
        this.total = total;
        this.taxes = taxes;
        this.discount = discount;
        this.date = date;
    }

    public Long getId(){ return id; }

    public void setId(Long id){ this.id = id; }

    public UserDto getUser(){ return userDto; }

    public void setUser(UserDto userDto){ this.userDto = userDto; }

    public List<ProductDto> getProduct(){ return productDtos; }

    public void setProduct(List<ProductDto> productDtos) { this.productDtos = productDtos; }

    public void setTotal(Double total){ this.total = total; }

    public Double getTotal(){ return total; }

    public void setTaxes(Double taxes){ this.taxes = taxes; }

    public Double getTaxes(){ return taxes; }

    public void setDiscount(Double discount){ this.discount = discount; }

    public Double getDiscount(){ return discount; }

    public LocalDate getDate(){ return date; }

    public void setDate(LocalDate date){ this.date = date; }

}
