package com.shopnest.service;

import com.shopnest.model.Product;
import com.shopnest.repository.ProductRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.*;

@Service
public class CartService {
    public static final String KEY = "shopnestCart";
    private final ProductRepository products;
    public CartService(ProductRepository products){this.products=products;}
    @SuppressWarnings("unchecked")
    public Map<Long,Integer> getCart(HttpSession session) {
        Object value=session.getAttribute(KEY);
        if(value instanceof Map<?,?>) return (Map<Long,Integer>) value;
        Map<Long,Integer> cart=new LinkedHashMap<>(); session.setAttribute(KEY,cart); return cart;
    }
    public void add(HttpSession session, Long id) {
        if(!products.existsById(id)) throw new IllegalArgumentException("Product not found.");
        Map<Long,Integer> cart=getCart(session); cart.put(id, Math.min(99, cart.getOrDefault(id,0)+1));
    }
    public List<CartLine> lines(HttpSession session) {
        List<CartLine> lines=new ArrayList<>();
        getCart(session).forEach((id,qty)->products.findById(id).ifPresent(p->lines.add(new CartLine(p,qty))));
        return lines;
    }
    public BigDecimal total(HttpSession session) {
        return lines(session).stream().map(CartLine::subtotal).reduce(BigDecimal.ZERO,BigDecimal::add);
    }
    public record CartLine(Product product, int quantity) {
        public BigDecimal subtotal(){return product.getPrice().multiply(BigDecimal.valueOf(quantity));}
    }
}
