package com.example.devApplication.controller;

import com.example.devApplication.entity.Product;
import com.example.devApplication.entity.User;
import com.example.devApplication.service.AdminService;
import com.example.devApplication.service.ProductService;
import com.example.devApplication.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final UserService userService;
    private final AdminService adminService;

    @GetMapping("/products")
    public String stocks(Model model, Principal principal) {
        if (userService.getRole(principal.getName()).equals("ADMIN")) {
            model.addAttribute("users", adminService.getUsers());
            model.addAttribute("getUser", new User());
            return "admin";
        } else {
            model.addAttribute("products", productService.getAllProducts());
            return "products";
        }
    }

    @GetMapping("/createProduct")
    public String createStock(Model model) {
        model.addAttribute("newProduct", new Product());
        return "createNewProduct";
    }

    @PostMapping("/saveProduct")
    public String saveStock(
            @ModelAttribute("newProduct") @Valid Product product,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors())
            return "createNewProduct";

        productService.createProduct(product);
        return "redirect:/products";
    }

    @GetMapping("/search")
    public String searchStock(
            @RequestParam String param,
            Model model
    ) {
        if (param != null) {
            model.addAttribute("products", productService.searchProduct(param));
        } else {
            model.addAttribute("products", productService.getAllProducts());
        }
        return "products";
    }
}
