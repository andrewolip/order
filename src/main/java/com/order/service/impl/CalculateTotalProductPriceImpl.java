package com.order.service.impl;

import com.order.dto.CalculatedOrderDto;
import com.order.dto.OrderDto;
import com.order.service.CalculateTotalProductPriceService;
import com.order.service.SaveOrderService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CalculateTotalProductPriceImpl implements CalculateTotalProductPriceService {

    private final SaveOrderService saveOrderService;

    public CalculateTotalProductPriceImpl(SaveOrderService saveOrderService) {
        this.saveOrderService = saveOrderService;
    }

    // TODO: avoid duplicates order
    @Transactional
    @Override
    public List<CalculatedOrderDto> calculate(Set<OrderDto> orders) {
        if (orders == null || orders.isEmpty()) {
            throw new IllegalArgumentException("Orders cannot be null or empty");
        }

        return orders.stream()
                .map(orderDto -> {
                    var orderTotalPrice = calculate(orderDto);
                    var orderDetailDto = updateOrderDetails(orderDto, orderTotalPrice);
                    var order = saveOrderService.save(orderDetailDto);

                    return new CalculatedOrderDto(order, orderTotalPrice);
                })
                .collect(Collectors.toList());
    }

    private BigDecimal calculate(OrderDto order) {
        if (order.getProducts() == null || order.getProducts().isEmpty()) {
            throw new IllegalArgumentException("Products cannot be null or empty");
        }
        return order.getProducts().stream()
                .map(productDto -> productDto.getPrice().multiply(BigDecimal.valueOf(productDto.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private OrderDto updateOrderDetails(OrderDto orderDto, BigDecimal totalPrice) {
        // todo: create a builder
        return new OrderDto(
                orderDto.getOrderId(),
                orderDto.getCustomer(),
                "COMPLETED",// todo: create an ENUM
                orderDto.getProducts(),
                totalPrice,
                LocalDateTime.now()
        );
    }
}