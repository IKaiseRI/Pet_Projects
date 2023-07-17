package com.example.devApplication.controller;

import com.example.devApplication.entity.User;
import com.example.devApplication.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @PostMapping("/getUserInfo")
    public String postRoles(
            @ModelAttribute("getUser") User user,
            Model model){
        User newUser = adminService.getUserId(user.getId());
        System.out.println(newUser.getUsername());
      //  model.addAttribute("user", user);
        return "modifyRoles";
    }
}
