package com.order.service;

import com.order.dto.OrderDto;
import com.order.entity.Order;

public interface SaveOrderService {
    Order save(OrderDto order);
}
