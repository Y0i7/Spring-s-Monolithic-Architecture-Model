package com.yoi.application.Service;

import com.yoi.application.Model.Product;

import java.util.List;

/*
 * @author Yoi
 * @date 2025/04/17
 * @description ProductService interface for product-related operations.
 */
public interface ProductService {
    List<Product> getAllProducts();
    Product getProductById(Long id);
    Product saveProduct(Product product);
    Product updateProduct(Long id, Product product);
    Product deleteProduct(Long id);
}
