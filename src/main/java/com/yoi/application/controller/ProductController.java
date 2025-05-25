package com.yoi.application.controller;

import com.yoi.application.config.exceptions.BadRequestException;
import com.yoi.application.model.ProductDto;
import com.yoi.application.service.ProductService;
import com.yoi.application.service.impl.ProductServiceDBImpl;
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
 * @description ProductController.java
 */
@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private final ProductServiceDBImpl productService;

    public ProductController(ProductServiceDBImpl productService) {
        this.productService = productService;
    }

    @GetMapping()
    public String getAllProducts(Model model) {
        List<ProductDto> productDtos = productService.getAllProducts();
        model.addAttribute("products", productDtos);
        return "products/list";
    }

    /*
     * Create Paths:
     */
    @GetMapping("/new")
    public String showCreateProductForm(Model model) {
        model.addAttribute("product", new ProductDto());
        return "products/create";
    }

    @PostMapping
    public String createProduct(@Valid @ModelAttribute("product") ProductDto productDto,
                                BindingResult result) {
        if (result.hasErrors()) {
            throw new BadRequestException("Datos de producto inválidos");
        }
        productService.saveProduct(productDto);
        return "redirect:/products";
    }

    /*
     * Update Paths:
     */

    @GetMapping("/edit/{id}")
    public String showEditProductForm(@PathVariable Long id, Model model) {
        ProductDto productDto = productService.getProductById(id);
        if (productDto != null) {
            model.addAttribute("product", productDto);
            return "products/edit";
        }
        return "redirect:/products";
    }

    @PostMapping("/update/{id}")
    public String updateProduct(@PathVariable Long id,
                                @Valid @ModelAttribute("product") ProductDto productDto, BindingResult result,
                                Model model) {
        if (result.hasErrors()) {
            model.addAttribute("product", productDto);
            return "products/edit";
        }
        try{
            productService.updateProduct(id, productDto);
        } catch (Exception e) {
            model.addAttribute("error", "Error updating product: " + e.getMessage());
            return "products/edit";
        }
        return "redirect:/products";
    }

    /*
     * Delete Paths:
     */
    @PostMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
        return "redirect:/products";
    }

}
