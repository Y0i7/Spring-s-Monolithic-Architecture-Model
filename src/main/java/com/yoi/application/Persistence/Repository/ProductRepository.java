package com.yoi.application.Persistence.Repository;

import com.yoi.application.Persistence.DAO.ProductDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/*
 * @author Yoi
 * @date 2025/04/17
 * @description ProductRepository interface for product-related database operations.
 */
@Repository
public interface ProductRepository extends JpaRepository<ProductDAO, Long> {}
            // Custom query methods can be defined here if needed
