package com.shopnest.controller;

import com.shopnest.service.CartService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@Controller
public class CartController {
    private final CartService cart;
    public CartController(CartService cart){this.cart=cart;}
    @PostMapping("/cart/add/{id}")
    public String add(@PathVariable Long id,HttpSession session){
        try {cart.add(session,id);} catch(IllegalArgumentException ignored) {}
        return "redirect:/cart";
    }
    @GetMapping("/cart")
    public String view(HttpSession session,Model model){
        model.addAttribute("lines",cart.lines(session));model.addAttribute("total",cart.total(session));return "cart";
    }
    @PostMapping("/cart/update")
    public String update(@RequestParam Map<String,String> params,HttpSession session) {
        var current=cart.getCart(session);
        for(var entry:params.entrySet()) {
            if(entry.getKey().startsWith("qty_")) {
                try {
                    Long id=Long.parseLong(entry.getKey().substring(4));
                    int qty=Integer.parseInt(entry.getValue());
                    if(qty<=0) current.remove(id);
                    else if(qty<=99 && current.containsKey(id)) current.put(id,qty);
                } catch(NumberFormatException ignored) {}
            }
        }
        return "redirect:/cart";
    }
    @PostMapping("/cart/remove/{id}")
    public String remove(@PathVariable Long id,HttpSession session){cart.getCart(session).remove(id);return "redirect:/cart";}
}
