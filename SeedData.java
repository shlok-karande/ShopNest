package com.shopnest.service;

import com.shopnest.model.Product;
import com.shopnest.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import java.util.List;

@Component
public class SeedData implements CommandLineRunner {
    private final ProductRepository products;
    public SeedData(ProductRepository products){this.products=products;}
    @Override public void run(String... args) {
        if (products.count() > 0) return;
        products.saveAll(List.of(
          new Product("Studio Wireless Headphones","Comfortable over-ear headphones with rich sound, cushioned pads and up to 30 hours of demo battery life.","Electronics",new BigDecimal("2499.00"),"https://images.unsplash.com/photo-1505740420928-5e560c06d30e?auto=format&fit=crop&w=900&q=80","Popular"),
          new Product("Everyday Smart Watch","A lightweight everyday watch concept with activity summaries and a bright display.","Electronics",new BigDecimal("3299.00"),"https://images.unsplash.com/photo-1523275335684-37898b6baf30?auto=format&fit=crop&w=900&q=80","New"),
          new Product("Canvas Daypack","A durable daypack for college, commuting and short weekend plans.","Fashion",new BigDecimal("1199.00"),"https://images.unsplash.com/photo-1553062407-98eeb64c6a62?auto=format&fit=crop&w=900&q=80","Everyday"),
          new Product("Minimal Desk Lamp","Warm, focused light for reading and late study sessions.","Home",new BigDecimal("899.00"),"https://images.unsplash.com/photo-1507473885765-e6ed057f782c?auto=format&fit=crop&w=900&q=80",""),
          new Product("Ceramic Coffee Set","A simple ceramic cup set for a calm start to the day.","Home",new BigDecimal("699.00"),"https://images.unsplash.com/photo-1514228742587-6b1558fcca3d?auto=format&fit=crop&w=900&q=80",""),
          new Product("Running Sneakers","Lightweight everyday sneakers designed for comfort on the move.","Fashion",new BigDecimal("1899.00"),"https://images.unsplash.com/photo-1542291026-7eec264c27ff?auto=format&fit=crop&w=900&q=80","Trending"),
          new Product("Compact Bluetooth Speaker","A portable speaker for music at home or outdoors.","Electronics",new BigDecimal("1599.00"),"https://images.unsplash.com/photo-1608043152269-423dbba4e7e1?auto=format&fit=crop&w=900&q=80",""),
          new Product("Soft Knit Throw","A cosy textured throw to add warmth to your reading corner.","Home",new BigDecimal("999.00"),"https://images.unsplash.com/photo-1600210492486-724fe5c67fb0?auto=format&fit=crop&w=900&q=80","")
        ));
    }
}
