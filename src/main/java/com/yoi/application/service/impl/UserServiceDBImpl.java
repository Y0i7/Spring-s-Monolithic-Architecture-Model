package com.yoi.application.service.impl;

import com.yoi.application.mapper.UserMapper;
import com.yoi.application.model.UserDto;
import com.yoi.application.persistence.dao.UserDAO;
import com.yoi.application.persistence.repository.UserRepository;
import com.yoi.application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/*
 * @author Yoi
 * @date 2025/04/17
 * @description UserServiceDBImpl class for user-related operations using a database.
 */

@Service
public class UserServiceDBImpl implements UserService {
    // This class implements the UserService interface for database operations.
    private final UserRepository userRepository;

    @Autowired // Constructor-based dependency injection
    public UserServiceDBImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /*
     * @description This method retrieves all users from the database.
     * @return List<User> - A list of User objects.
     */
    @Override
    public List<UserDto> getAllUsers(){
        List<UserDAO> Entities = userRepository.findAll();
        return UserMapper.toDtoList(Entities);
    }

    /*
     * @description This method retrieves a user by its ID from the database.
     * @param id - The ID of the user to retrieve.
     * @return User - The User object with the specified ID, or null if not found.
     */
    @Override
    public UserDto getUserById(Long id){
        Optional<UserDAO> optionalEntity = userRepository.findById(id);
        return optionalEntity
                .map(UserMapper::toDto)
                .orElse(null);
    }

    /*
     * @description This method saves a new user to the database.
     * @param user - The User object to save.
     * @return User - The saved User object.
     */
    @Override
    public UserDto saveUser(UserDto userDto){
        UserDAO entity = UserMapper.toEntity(userDto);
        return UserMapper.toDto(userRepository.save(entity));
    }

    /*
     * @description This method updates an existing user in the database.
     * @param id - The ID of the user to update.
     * @param user - The User object with updated information.
     * @return User - The updated User object, or null if not found.
     */
    @Override
    public UserDto updateUser(Long id, UserDto userDto) {
        Optional<UserDAO> optionaEntity = userRepository.findById(id);
        if (optionaEntity.isPresent()) {
            UserDAO entity = optionaEntity.get();
            entity.setName(userDto.getName());
            entity.setEmail(userDto.getEmail());
            return UserMapper.toDto(userRepository.save(entity));
        }
        return null;
    }

    /*
     * @description This method updates an existing user in the database.
     * @param id - The ID of the user to update.
     * @param user - The User object with updated information.
     * @return User - The updated User object, or null if not found.
     */
    @Override
    public UserDto deleteUser(Long id){
        Optional<UserDAO> optionalEntity = userRepository.findById(id);
        if (optionalEntity.isPresent()){
            userRepository.deleteById(id);
            return optionalEntity
                    .map(UserMapper::toDto)
                    .orElse(null);
        }
        return null;
    }
}
