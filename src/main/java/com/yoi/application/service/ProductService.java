package com.yoi.application.service;

import com.yoi.application.model.ProductDto;

import java.util.List;

/*
 * @author Yoi
 * @date 2025/04/17
 * @description ProductService interface for product-related operations.
 */
public interface ProductService {
    List<ProductDto> getAllProducts();
    ProductDto getProductById(Long id);
    ProductDto saveProduct(ProductDto productDto);
    ProductDto updateProduct(Long id, ProductDto productDto);
    ProductDto deleteProduct(Long id);
}
