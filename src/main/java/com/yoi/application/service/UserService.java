package com.yoi.application.service;

import com.yoi.application.model.UserDto;

import java.util.List;

/*
 * @author Yoi
 * @date 2025/04/17
 * @description UserService interface for user-related operations.
 */

public interface UserService {
    List<UserDto> getAllUsers();
    UserDto getUserById(Long id);
    UserDto saveUser(UserDto userDto);
    UserDto updateUser(Long id, UserDto userDto);
    UserDto deleteUser(Long id);
}
