package com.shopnest.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="shop_orders")
public class ShopOrder {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @ManyToOne(optional=false) private Customer customer;
    @Column(nullable=false) private String deliveryAddress;
    @Column(nullable=false, precision=10, scale=2) private BigDecimal total;
    @Column(nullable=false) private LocalDateTime createdAt = LocalDateTime.now();
    @OneToMany(mappedBy="order", cascade=CascadeType.ALL, orphanRemoval=true)
    private List<OrderItem> items = new ArrayList<>();

    protected ShopOrder() {}
    public ShopOrder(Customer customer, String deliveryAddress, BigDecimal total) {
        this.customer=customer; this.deliveryAddress=deliveryAddress; this.total=total;
    }
    public void addItem(OrderItem item){items.add(item); item.setOrder(this);}
    public Long getId(){return id;} public Customer getCustomer(){return customer;}
    public String getDeliveryAddress(){return deliveryAddress;} public BigDecimal getTotal(){return total;}
    public LocalDateTime getCreatedAt(){return createdAt;} public List<OrderItem> getItems(){return items;}
}
