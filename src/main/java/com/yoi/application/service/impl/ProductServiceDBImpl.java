package com.yoi.application.service.impl;

import com.yoi.application.mapper.ProductMapper;
import com.yoi.application.model.ProductDto;
import com.yoi.application.persistence.dao.ProductDAO;
import com.yoi.application.persistence.repository.ProductRepository;
import com.yoi.application.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/*
 * @author Yoi
 * @date 2025/04/17
 * @description ProductServiceDBImpl class for product-related operations.
 */

@Service
public class ProductServiceDBImpl implements ProductService {

    @Autowired // Constructor-based dependency injection
    private final ProductRepository productRepository;


    public ProductServiceDBImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /*
     * @description This method retrieves all products from the database.
     * @return List<Product> - A list of Product objects.
     */
    @Override
    public List<ProductDto> getAllProducts() {
        List<ProductDAO> entities = productRepository.findAll();
        return ProductMapper.toDtoList(entities);
    }

    /*
     * @description This method retrieves a product by its ID from the database.
     * @param id - The ID of the product to retrieve.
     * @return Product - The Product object with the specified ID, or null if not found.
     */
    @Override
    public ProductDto getProductById(Long id) {
        Optional<ProductDAO> optionalEntity = productRepository.findById(id);
        return optionalEntity
                .map(ProductMapper::toDto)
                .orElse(null);
    }

    /*
     * @description This method saves a new product to the database.
     * @param product - The Product object to save.
     * @return Product - The saved Product object.
     */
    @Override
    public ProductDto saveProduct(ProductDto productDto) {
        ProductDAO entity = ProductMapper.toEntity(productDto);
        return ProductMapper.toDto(productRepository.save(entity));
    }

    /*
     * @description This method updates an existing product in the database.
     * @param id - The ID of the product to update.
     * @param product - The Product object with updated information.
     * @return Product - The updated Product object, or null if not found.
     */
    @Override
    public ProductDto updateProduct(Long id, ProductDto productDto) {
        Optional<ProductDAO> optionalEntity = productRepository.findById(id);
        if (optionalEntity.isPresent()) {
            ProductDAO entity = optionalEntity.get();
            entity.setName(productDto.getName());
            entity.setPrice(productDto.getPrice());
            return ProductMapper.toDto(productRepository.save(entity));
        }
        return null;
    }

    /*
     * @description This method deletes a product by its ID from the database.
     * @param id - The ID of the product to delete.
     * @return Product - The deleted Product object, or null if not found.
     */
    @Override
    public ProductDto deleteProduct(Long id) {
        Optional<ProductDAO> optionalEntity = productRepository.findById(id);
        if (optionalEntity.isPresent()) {
            productRepository.deleteById(id);
            return optionalEntity
                    .map(ProductMapper::toDto)
                    .orElse(null);
        }
        return null;
    }
}
