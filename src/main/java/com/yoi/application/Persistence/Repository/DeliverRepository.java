package com.yoi.application.Persistence.Repository;

import com.yoi.application.Persistence.DAO.DeliverDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/*
 * @author Yoi
 * @date 2025/04/17
 * @description DeliverRepository interface for deliver-related database operations.
 */
@Repository
public interface DeliverRepository extends JpaRepository<DeliverDAO, Long> {}
    // Custom query methods can be defined here if needed
