package com.shopnest.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
public class OrderItem {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @ManyToOne(optional=false) private ShopOrder order;
    @Column(nullable=false) private String productName;
    @Column(nullable=false) private int quantity;
    @Column(nullable=false, precision=10, scale=2) private BigDecimal unitPrice;

    protected OrderItem() {}
    public OrderItem(String productName, int quantity, BigDecimal unitPrice) {
        this.productName=productName; this.quantity=quantity; this.unitPrice=unitPrice;
    }
    public void setOrder(ShopOrder order){this.order=order;}
    public String getProductName(){return productName;} public int getQuantity(){return quantity;}
    public BigDecimal getUnitPrice(){return unitPrice;}
}
