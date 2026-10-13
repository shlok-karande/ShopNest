package com.shopnest.service;

import com.shopnest.model.*;
import com.shopnest.repository.ShopOrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;

@Service
public class OrderService {
    private final ShopOrderRepository orders;
    public OrderService(ShopOrderRepository orders){this.orders=orders;}
    @Transactional
    public ShopOrder place(Customer customer, String address, CartService cartService, jakarta.servlet.http.HttpSession session) {
        var lines=cartService.lines(session);
        if(lines.isEmpty()) throw new IllegalArgumentException("Your cart is empty.");
        if(address==null || address.trim().length()<8) throw new IllegalArgumentException("Please enter a complete delivery address (at least 8 characters).");
        BigDecimal total=cartService.total(session);
        ShopOrder order=new ShopOrder(customer,address.trim(),total);
        for(var line:lines) order.addItem(new OrderItem(line.product().getName(),line.quantity(),line.product().getPrice()));
        ShopOrder saved=orders.save(order);
        cartService.getCart(session).clear();
        return saved;
    }
    public ShopOrder findForCustomer(Long orderId, Long customerId) {
        return orders.findByIdAndCustomerId(orderId,customerId)
            .orElseThrow(()->new IllegalArgumentException("Order not found."));
    }
}
