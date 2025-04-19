package com.yoi.application.Controller;

import com.yoi.application.Model.User;
import com.yoi.application.Service.UserService;
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
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "users/list";
    }

    /*
     * Create Paths:
     */
    @GetMapping("/new")
    public String showCreateUserForm(Model model) {
        model.addAttribute("user", new User());
        return "users/create";
    }

    @PostMapping
    public String createUser(@Valid @ModelAttribute("user") User user, BindingResult result) {
        if (result.hasErrors()) {
            return "users/create";
        }
        userService.saveUser(user);
        return "redirect:/users";
    }

    /*
     * Update Paths:
     */
    @GetMapping("/edit/{id}")
    public String showEditUserForm(@PathVariable Long id, Model model) {
        User user = userService.getUserById(id);
        if (user != null) {
            model.addAttribute("user", user);
            return "users/edit";
        }
        return "redirect:/users";
    }

    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable Long id,
                             @Valid @ModelAttribute("user") User user, BindingResult result,
                             Model model) {
        if (result.hasErrors()) {
            model.addAttribute("user", user);
            return "users/edit";
        }
        try{
            userService.updateUser(id, user);
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
