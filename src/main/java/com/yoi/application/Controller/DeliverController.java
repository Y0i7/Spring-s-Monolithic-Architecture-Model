package com.yoi.application.Controller;

import com.yoi.application.Model.Deliver;
import com.yoi.application.Service.DeliverService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        List<Deliver> delivers = deliverService.getAllDelivers();
        model.addAttribute("delivers", delivers);
        return "delivers/list";
    }

    /*
     * Create Paths:
     */

    @GetMapping("/new")
    public String showCreateDeliverForm(Model model) {
        model.addAttribute("deliver", new Deliver());
        return "delivers/create";
    }

    @PostMapping
    public String createDeliver(@Valid @ModelAttribute("deliver") Deliver deliver, BindingResult result) {
        if(result.hasErrors()) {
            return "delivers/create";
        }
        deliverService.saveDeliver(deliver);
        return "redirect:/delivers";
    }

    /*
     * Update Paths:
     */
    @GetMapping("/edit/{id}")
    public String showEditDeliverForm(@PathVariable Long id , Model model) {
        Deliver deliver = deliverService.getDeliverById(id);
        if (deliver != null) {
            model.addAttribute("deliver", deliver);
            return "delivers/edit";
        }
        return "redirect:/delivers";
    }

    @PostMapping("/update/{id}")
    public String updateDeliver(@PathVariable Long id,
                                @Valid @ModelAttribute("deliver") Deliver deliver, BindingResult result,
                                Model model) {
        if (result.hasErrors()) {
            model.addAttribute("deliver", deliver);
            return "delivers/edit";
        }
        try {
            deliverService.updateDeliver(id, deliver);
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
