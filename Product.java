package com.shopnest.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
public class Product {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable=false) private String name;
    @Column(length=1200) private String description;
    @Column(nullable=false) private String category;
    @Column(nullable=false, precision=10, scale=2) private BigDecimal price;
    private String imageUrl;
    private String badge;

    protected Product() {}
    public Product(String name, String description, String category, BigDecimal price, String imageUrl, String badge) {
        this.name=name; this.description=description; this.category=category; this.price=price; this.imageUrl=imageUrl; this.badge=badge;
    }
    public Long getId(){return id;} public String getName(){return name;}
    public String getDescription(){return description;} public String getCategory(){return category;}
    public BigDecimal getPrice(){return price;} public String getImageUrl(){return imageUrl;} public String getBadge(){return badge;}
}
