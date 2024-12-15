package com.order.service.impl;

import com.order.dto.OrderDto;
import com.order.mapper.OrderMapper;
import com.order.service.CalculateTotalProductPriceService;
import com.order.service.OrderProcessorService;
import com.order.service.SaveOrderService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class OrderProcessorServiceImpl implements OrderProcessorService {
    private final SaveOrderService saveOrderService;
    private final CalculateTotalProductPriceService calculateService;
    private final OrderMapper mapper;

    public OrderProcessorServiceImpl(
            CalculateTotalProductPriceService calculateService,
            SaveOrderService saveOrderService,
            OrderMapper mapper
    ) {
        this.saveOrderService = saveOrderService;
        this.calculateService = calculateService;
        this.mapper = mapper;
    }

    @Override
    public List<OrderDto> execute(Set<OrderDto> orders) {
        return saveOrderService.save(calculateService.calculate(orders)).stream()
                .map(mapper::toDto)
                .toList();
    }
}
