package com.order.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import org.springframework.data.annotation.CreatedDate;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Set;

@Table(name = "orders")
@Entity
public class Order implements Serializable {
    private Long id;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "order_id")
    private String orderId;

    private String customer;

    private String status;

    @OneToMany(cascade = CascadeType.PERSIST)
    private Set<Product> products;

    @CreatedDate
    private ZonedDateTime createDate;

    public Order(){}

    public Order(Long id, String orderId, String customer, String status, Set<Product> products, ZonedDateTime createDate) {
        this.id = id;
        this.orderId = orderId;
        this.customer = customer;
        this.status = status;
        this.products = products;
        this.createDate = createDate;
    }

    public Order(Long id, String customer, String status, Set<Product> products) {
        this.id = id;
        this.customer = customer;
        this.status = status;
        this.products = products;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
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

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public ZonedDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(ZonedDateTime createDate) {
        this.createDate = createDate;
    }
}