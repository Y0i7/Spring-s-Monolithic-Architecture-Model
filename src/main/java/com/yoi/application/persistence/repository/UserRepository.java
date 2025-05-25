package com.yoi.application.persistence.repository;

import com.yoi.application.persistence.dao.UserDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/*
 * @author Yoi
 * @date 2025/04/17
 * @description UserRepository interface for user-related database operations.
 */
@Repository
public interface UserRepository extends JpaRepository<UserDAO, Long> {}
        // Custom query methods can be defined here if needed