package com.order.service;

import com.order.dto.OrderDto;

import java.util.List;
import java.util.Set;

public interface OrderProcessorService {
    List<OrderDto> execute(Set<OrderDto> orders);
}
