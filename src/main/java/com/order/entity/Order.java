package com.order.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Table(name = "orders")
@Entity
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private Long orderId;

    private String customer;

    private String status;

    private BigDecimal totalPrice;

    @OneToMany(cascade = CascadeType.PERSIST)
    private Set<Product> products;

    private LocalDateTime createDate;

    public Order(){}

    public Order(String id, Long orderId, String customer, String status, Set<Product> products, LocalDateTime createDate) {
        this.id = id;
        this.orderId = orderId;
        this.customer = customer;
        this.status = status;
        this.products = products;
        this.createDate = createDate;
    }

    public Order(Long orderId, String customer, String status, Set<Product> products, BigDecimal totalPrice) {
        this.orderId = orderId;
        this.customer = customer;
        this.status = status;
        this.products = products;
        this.totalPrice = totalPrice;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }
}