package com.yoi.application.persistence.repository;

import com.yoi.application.persistence.dao.DeliverDAO;
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
