package com.shopnest.controller;

import com.shopnest.repository.ProductRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class StoreController {
    private final ProductRepository products;
    public StoreController(ProductRepository products){this.products=products;}

    @GetMapping("/")
    public String home(@RequestParam(defaultValue="") String q,
                       @RequestParam(defaultValue="") String category, Model model) {
        String query=q.trim().substring(0, Math.min(q.trim().length(),100));
        model.addAttribute("products",products.search(query,category));
        model.addAttribute("q",query); model.addAttribute("selectedCategory",category);
        model.addAttribute("categories",products.findAll().stream().map(p->p.getCategory()).distinct().sorted().toList());
        model.addAttribute("resultCount",products.search(query,category).size());
        return "index";
    }
    @GetMapping("/product/{id}")
    public String detail(@PathVariable Long id, Model model) {
        var product=products.findById(id).orElse(null);
        if(product==null) return "redirect:/";
        model.addAttribute("product",product); return "product";
    }
}
