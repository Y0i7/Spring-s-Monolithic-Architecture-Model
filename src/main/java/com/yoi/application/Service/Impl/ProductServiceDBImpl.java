package com.yoi.application.Service.Impl;

import com.yoi.application.Mapper.ProductMapper;
import com.yoi.application.Model.Product;
import com.yoi.application.Persistence.DAO.ProductDAO;
import com.yoi.application.Persistence.Repository.ProductRepository;
import com.yoi.application.Service.ProductService;
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

    // This class implements the ProductService interface for database operations.
    private final ProductRepository productRepository;

    @Autowired // Constructor-based dependency injection
    public ProductServiceDBImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /*
     * @description This method retrieves all products from the database.
     * @return List<Product> - A list of Product objects.
     */
    @Override
    public List<Product> getAllProducts() {
        List<ProductDAO> entities = productRepository.findAll();
        return ProductMapper.toDtoList(entities);
    }

    /*
     * @description This method retrieves a product by its ID from the database.
     * @param id - The ID of the product to retrieve.
     * @return Product - The Product object with the specified ID, or null if not found.
     */
    @Override
    public Product getProductById(Long id) {
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
    public Product saveProduct(Product product) {
        ProductDAO entity = ProductMapper.toEntity(product);
        return ProductMapper.toDto(productRepository.save(entity));
    }

    /*
     * @description This method updates an existing product in the database.
     * @param id - The ID of the product to update.
     * @param product - The Product object with updated information.
     * @return Product - The updated Product object, or null if not found.
     */
    @Override
    public Product updateProduct(Long id, Product product) {
        Optional<ProductDAO> optionalEntity = productRepository.findById(id);
        if (optionalEntity.isPresent()) {
            ProductDAO entity = optionalEntity.get();
            entity.setName(product.getName());
            entity.setPrice(product.getPrice());
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
    public Product deleteProduct(Long id) {
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
