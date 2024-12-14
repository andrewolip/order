package com.order.dto;

import java.time.ZonedDateTime;
import java.util.Set;

public class OrderDto {
    private String id;
    private String customer;
    private Set<ProductDto> products;
    private String status;
    private ZonedDateTime createDate;

    public OrderDto() {}

    public OrderDto(String id, String customer, Set<ProductDto> products, String status, ZonedDateTime createDate) {
        this.id = id;
        this.customer = customer;
        this.products = products;
        this.status = status;
        this.createDate = createDate;
    }

    public OrderDto(String customer, String status, Set<ProductDto> products) {
        this.customer = customer;
        this.products = products;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

