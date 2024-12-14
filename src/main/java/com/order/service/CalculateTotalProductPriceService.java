package com.order.service;

import com.order.dto.CalculatedOrderDto;
import com.order.dto.OrderDto;

import java.util.List;
import java.util.Set;

public interface CalculateTotalProductPriceService {
   List<CalculatedOrderDto> calculate(Set<OrderDto> orders);
}
