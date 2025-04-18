package com.yoi.application.Controller;

import com.yoi.application.Model.Product;
import com.yoi.application.Service.ProductService;
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
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String getAllProducts(Model model) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "products/list";
    }

    /*
     * Create Paths:
     */
    @GetMapping("/new")
    public String showCreateProductForm(Model model) {
        model.addAttribute("product", new Product());
        return "products/create";
    }

    @PostMapping
    public String createProduct(@Valid @ModelAttribute("product") Product product, BindingResult result) {
        productService.saveProduct(product);
        if (result.hasErrors()) {
            return "products/create";
        }
        return "redirect:/products";
    }

    /*
     * Update Paths:
     */

    @GetMapping("/edit/{id}")
    public String showEditProductForm(@PathVariable Long id, Model model) {
        Product product = productService.getProductById(id);
        if (product != null) {
            model.addAttribute("product", product);
            return "products/edit";
        }
        return "redirect:/products";
    }

    @PostMapping("/update/{id}")
    public String updateProduct(@PathVariable Long id, @Valid @ModelAttribute("product") Product product, BindingResult result) {
        if (result.hasErrors()) {
            return "products/edit";
        }
        productService.updateProduct(id, product);
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
