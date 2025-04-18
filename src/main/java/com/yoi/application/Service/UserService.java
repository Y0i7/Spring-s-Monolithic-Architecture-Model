package com.yoi.application.Service;

import com.yoi.application.Model.User;

import java.util.List;

/*
 * @author Yoi
 * @date 2025/04/17
 * @description UserService interface for user-related operations.
 */

public interface UserService {
    List<User> getAllUsers();
    User getUserById(Long id);
    User saveUser(User user);
    User updateUser(Long id, User user);
    User deleteUser(Long id);
}
