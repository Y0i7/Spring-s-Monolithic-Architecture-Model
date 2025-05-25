package com.yoi.application.controller;

import com.yoi.application.model.DeliverDto;
import com.yoi.application.service.DeliverService;
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
@RequestMapping("/delivers")
public class DeliverController {

    @Autowired
    private final DeliverService deliverService;

    public DeliverController(DeliverService deliverService) {
        this.deliverService = deliverService;
    }

    @GetMapping
    public String getAllDelivers(Model model) {
        List<DeliverDto> deliverDtos = deliverService.getAllDelivers();
        model.addAttribute("delivers", deliverDtos);
        return "delivers/list";
    }

    /*
     * Create Paths:
     */

    @GetMapping("/new")
    public String showCreateDeliverForm(Model model) {
        model.addAttribute("deliver", new DeliverDto());
        return "delivers/create";
    }

    @PostMapping
    public String createDeliver(@Valid @ModelAttribute("deliver") DeliverDto deliverDto, BindingResult result) {
        if(result.hasErrors()) {
            return "delivers/create";
        }
        deliverService.saveDeliver(deliverDto);
        return "redirect:/delivers";
    }

    /*
     * Update Paths:
     */
    @GetMapping("/edit/{id}")
    public String showEditDeliverForm(@PathVariable Long id , Model model) {
        DeliverDto deliverDto = deliverService.getDeliverById(id);
        if (deliverDto != null) {
            model.addAttribute("deliver", deliverDto);
            return "delivers/edit";
        }
        return "redirect:/delivers";
    }

    @PostMapping("/update/{id}")
    public String updateDeliver(@PathVariable Long id,
                                @Valid @ModelAttribute("deliver") DeliverDto deliverDto, BindingResult result,
                                Model model) {
        if (result.hasErrors()) {
            model.addAttribute("deliver", deliverDto);
            return "delivers/edit";
        }
        try {
            deliverService.updateDeliver(id, deliverDto);
        }catch (Exception e) {
            model.addAttribute("error", "Error updating deliver: " + e.getMessage());
            return "delivers/edit";
        }
        return "redirect:/delivers";
    }

    /*
     * Delete Paths:
     */
    @PostMapping("/delete/{id}")
    public String deleteDeliver(@PathVariable Long id) {
        deliverService.deleteDeliver(id);
        return "redirect:/delivers";
    }
}
