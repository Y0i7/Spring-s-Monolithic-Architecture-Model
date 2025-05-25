package com.yoi.application.controller;

import com.yoi.application.model.UserDto;
import com.yoi.application.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
 * @author Yoi
 * @date 2025/04/18
 * @description DeliveryMapper.java
 */

@Controller
@RequestMapping("/users")
public class UserController {
  
    @Autowired
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getAllUsers(Model model) {
        List<UserDto> userDtos = userService.getAllUsers();
        model.addAttribute("users", userDtos);
        return "users/list";
    }

    /*
     * Create Paths:
     */
    @GetMapping("/new")
    public String showCreateUserForm(Model model) {
        model.addAttribute("user", new UserDto());
        return "users/create";
    }

    @PostMapping
    public String createUser(@Valid @ModelAttribute("user") UserDto userDto, BindingResult result) {
        if (result.hasErrors()) {
            return "users/create";
        }
        userService.saveUser(userDto);
        return "redirect:/users";
    }

    /*
     * Update Paths:
     */
    @GetMapping("/edit/{id}")
    public String showEditUserForm(@PathVariable Long id, Model model) {
        UserDto userDto = userService.getUserById(id);
        if (userDto != null) {
            model.addAttribute("user", userDto);
            return "users/edit";
        }
        return "redirect:/users";
    }

    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable Long id,
                             @Valid @ModelAttribute("user") UserDto userDto, BindingResult result,
                             Model model) {
        if (result.hasErrors()) {
            model.addAttribute("user", userDto);
            return "users/edit";
        }
        try{
            userService.updateUser(id, userDto);
        }catch (Exception e){
            model.addAttribute("error", "Error updating user: " + e.getMessage());
            return "users/edit";
        }
        return "redirect:/users";
    }

    /*
     * Delete Paths:
     */
    @PostMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }

}
