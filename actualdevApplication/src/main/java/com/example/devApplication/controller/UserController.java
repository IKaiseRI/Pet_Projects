package com.example.devApplication.controller;

import com.example.devApplication.entity.User;
import com.example.devApplication.service.AdminService;
import com.example.devApplication.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/logout")
    public String logout(){
        return "logout";
    }

    @GetMapping("/registration")
    public String register(Model model){
        model.addAttribute("newUser", new User());
        return "registration";
    }

    @PostMapping("/createUser")
    public String createUser(
            @ModelAttribute("newUser") @Valid User user,
            BindingResult bindingResult
    ){
        if (bindingResult.hasErrors())
            return "registration";

        userService.createUser(user);
        return "redirect:/login";
    }
}
