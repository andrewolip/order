package com.order.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

public class OrderDto {
    private String id;

    @NotNull private Long orderId;

    @NotBlank private String customer;

    @NotNull private Set<ProductDto> products;

    @NotBlank private String status;

    @NotNull private BigDecimal totalPrice;

    private LocalDateTime createDate;

    public OrderDto() {}

    public OrderDto(String id, Long orderId, String customer, Set<ProductDto> products, String status, BigDecimal totalPrice, LocalDateTime createDate) {
        this.id = id;
        this.orderId = orderId;
        this.customer = customer;
        this.products = products;
        this.status = status;
        this.totalPrice = totalPrice;
        this.createDate = createDate;
    }

    public OrderDto(Long orderId, String customer, String status, Set<ProductDto> products, BigDecimal totalPrice, LocalDateTime createDate) {
        this.orderId = orderId;
        this.customer = customer;
        this.products = products;
        this.status = status;
        this.totalPrice = totalPrice;
        this.createDate = createDate;
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

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        OrderDto orderDto = (OrderDto) o;
        return Objects.equals(orderId, orderDto.orderId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(orderId);
    }
}

