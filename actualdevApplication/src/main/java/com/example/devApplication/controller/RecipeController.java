package com.example.devApplication.controller;

import com.example.devApplication.entity.Product;
import com.example.devApplication.entity.Recipe;
import com.example.devApplication.entity.RequiredProducts;
import com.example.devApplication.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Controller
@RequiredArgsConstructor
public class RecipeController {
    private final RecipeService recipeService;
    private final ProductService productService;

    @GetMapping("/recipes")
    public String getRecipes(Principal principal, Model model){
        model.addAttribute("recipes",recipeService.getRecipes(principal.getName()));
        model.addAttribute("userQuantities",recipeService.getQuantities(principal.getName()));
        return "recipes";
    }
    @GetMapping("/createNewRecipe")
    public String createRecipe(Model model){
        model.addAttribute("products", productService.getAllProducts());
        return "createNewRecipe";
    }

    @GetMapping("/createRecipe")
    public String getByCheck(Model model){
        model.addAttribute("test", new RequiredProducts());
        model.addAttribute("products", productService.getAllProducts());
        return "createNewRecipe";
    }

    @PostMapping("/getIngredientByCheck")
    public String postByCheck(
            @ModelAttribute("test") RequiredProducts productNames,
            ModelMap model
    ){
        if(productNames == null)
            return "redirect:/createNewRecipe";
        else {
            List<String> products = Arrays.stream(productNames.getName().split(",")).toList();
            model.addAttribute("productNames", products);
            model.addAttribute("recipeName", productNames);
            return "setNewRecipeQuantities";
        }
    }
    @PostMapping("/getNewRecipe")
    public String postRecipeName(
            @ModelAttribute("recipeName") RequiredProducts name,
            Principal principal
    ){
        recipeService.createRecipe(name,principal.getName());
        return "redirect:/recipes";
    }

}
