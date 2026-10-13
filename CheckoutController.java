package com.shopnest.controller;

import com.shopnest.repository.CustomerRepository;
import com.shopnest.service.CartService;
import com.shopnest.service.OrderService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CheckoutController {
    private final CartService cart;
    private final OrderService orders;
    private final CustomerRepository customers;
    public CheckoutController(CartService cart,OrderService orders,CustomerRepository customers){this.cart=cart;this.orders=orders;this.customers=customers;}
    @GetMapping("/checkout")
    public String checkout(HttpSession session,Model model) {
        if(cart.lines(session).isEmpty()) return "redirect:/cart";
        if(session.getAttribute("customerId")==null) return "redirect:/login";
        model.addAttribute("lines",cart.lines(session));model.addAttribute("total",cart.total(session));return "checkout";
    }
    @PostMapping("/checkout")
    public String place(@RequestParam String address,HttpSession session,Model model) {
        Object id=session.getAttribute("customerId");
        if(id==null) return "redirect:/login";
        var customer=customers.findById((Long)id).orElse(null);
        if(customer==null){session.removeAttribute("customerId");return "redirect:/login";}
        try {
            var order=orders.place(customer,address,cart,session);
            return "redirect:/order/"+order.getId();
        } catch(IllegalArgumentException ex) {
            model.addAttribute("error",ex.getMessage());model.addAttribute("lines",cart.lines(session));model.addAttribute("total",cart.total(session));return "checkout";
        }
    }
    @GetMapping("/order/{id}")
    public String confirmation(@PathVariable Long id,HttpSession session,Model model) {
        Object customerId=session.getAttribute("customerId");
        if(customerId==null)return "redirect:/login";
        try {model.addAttribute("order",orders.findForCustomer(id,(Long)customerId));return "confirmation";}
        catch(IllegalArgumentException ex){return "redirect:/";}
    }
}
