package com.order.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.ZonedDateTime;
import java.util.Set;

public class OrderDto {
    private Long id;

    private String orderId;

    @NotBlank private String customer;

    @NotNull private Set<ProductDto> products;

    @NotBlank private String status;

    private ZonedDateTime createDate;

    public OrderDto() {}

    public OrderDto(Long id, String orderId, String customer, Set<ProductDto> products, String status, ZonedDateTime createDate) {
        this.id = id;
        this.orderId = orderId;
        this.customer = customer;
        this.products = products;
        this.status = status;
        this.createDate = createDate;
    }

    public OrderDto(Long id, String customer, String status, Set<ProductDto> products) {
        this.id = id;
        this.customer = customer;
        this.products = products;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String orderId() {
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

    public Set<ProductDto> getProducts() {
        return products;
    }

    public void setProducts(Set<ProductDto> products) {
        this.products = products;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ZonedDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(ZonedDateTime createDate) {
        this.createDate = createDate;
    }
}

