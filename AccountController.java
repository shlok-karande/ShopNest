package com.shopnest.controller;

import com.shopnest.service.AccountService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AccountController {
    private final AccountService accounts;
    public AccountController(AccountService accounts){this.accounts=accounts;}
    @GetMapping("/register") public String registerForm(){return "register";}
    @PostMapping("/register")
    public String register(@RequestParam String fullName,@RequestParam String email,@RequestParam String password,Model model,HttpSession session){
        try {
            var customer=accounts.register(fullName,email,password);
            session.setAttribute("customerId",customer.getId()); session.setAttribute("customerName",customer.getFullName());
            return "redirect:/";
        } catch(IllegalArgumentException ex){model.addAttribute("error",ex.getMessage());model.addAttribute("fullName",fullName);model.addAttribute("email",email);return "register";}
    }
    @GetMapping("/login") public String loginForm(){return "login";}
    @PostMapping("/login")
    public String login(@RequestParam String email,@RequestParam String password,Model model,HttpSession session){
        try {
            var customer=accounts.login(email,password);
            session.setAttribute("customerId",customer.getId()); session.setAttribute("customerName",customer.getFullName());
            return "redirect:/";
        } catch(IllegalArgumentException ex){model.addAttribute("error",ex.getMessage());model.addAttribute("email",email);return "login";}
    }
    @PostMapping("/logout") public String logout(HttpSession session){session.removeAttribute("customerId");session.removeAttribute("customerName");return "redirect:/";}
}
