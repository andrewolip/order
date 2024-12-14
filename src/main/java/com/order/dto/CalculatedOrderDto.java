package com.order.dto;

import com.order.entity.Order;

import java.math.BigDecimal;

public record CalculatedOrderDto(
        Order order,
        BigDecimal totalPrice) {
}
