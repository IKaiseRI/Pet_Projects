package com.example.devApplication.controller;

import com.example.devApplication.entity.DTO.UserProductDTO;
import com.example.devApplication.entity.UserProduct;
import com.example.devApplication.service.UserService;
import com.example.devApplication.service.UserProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
@Controller
@RequiredArgsConstructor
public class UserProductController {
    private final UserService userService;
    private final UserProductService userProductService;
    @GetMapping("/userProducts")
    public String userProducts(Model model, Principal principal){
        model.addAttribute("products", userProductService.getUserProduct(principal.getName()));
        return "userProducts";
    }

    @GetMapping("/createUserProducts")
    public String createUserProduct(Model model){
        model.addAttribute("newUserProduct", new UserProduct());
        return "createNewUserProducts";
    }

    @PostMapping("/saveUserProduct")
    public String saveUserProduct(
            @ModelAttribute("newUserProduct") @Valid UserProductDTO userProduct,
            BindingResult bindingResult,
            Principal principal,
            Model model
    ) throws Exception {
        if (bindingResult.hasErrors())
            return "createNewUserProducts";

        if (!userProductService.createUserProduct(userProduct, principal.getName())) {
            model.addAttribute("error","The inserted product doesn't exist in general list");
            return "createNewUserProducts";
        }else
            return "redirect:/userProducts";
    }

    @GetMapping("/deleteUserProduct/{id}")
    public String deleteUserProduct(
            @PathVariable("id") Long id
    ){
        userProductService.deleteById(id);
        return "redirect:/userProducts";
    }

}
