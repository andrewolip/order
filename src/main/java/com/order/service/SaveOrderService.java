package com.order.service;

import com.order.dto.OrderDto;
import com.order.entity.Order;

import java.util.List;
import java.util.Set;

public interface SaveOrderService {
    List<Order> save(Set<OrderDto> order);
}
