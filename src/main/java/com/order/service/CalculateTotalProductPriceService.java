package com.order.service;

import com.order.dto.OrderDto;
import jakarta.transaction.Transactional;

import java.util.Set;

public interface CalculateTotalProductPriceService {
   @Transactional
   Set<OrderDto> calculate(Set<OrderDto> orders);
}
