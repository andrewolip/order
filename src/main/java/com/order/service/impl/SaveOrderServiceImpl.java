package com.order.service.impl;

import com.order.dto.OrderDto;
import com.order.entity.Order;
import com.order.infrastructure.repository.OrderRepository;
import com.order.mapper.OrderMapper;
import com.order.service.SaveOrderService;
import org.springframework.stereotype.Service;

@Service
public class SaveOrderServiceImpl implements SaveOrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    public SaveOrderServiceImpl(
            OrderRepository orderRepository,
            OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
    }

    @Override
    public Order save(OrderDto orderDto) {
        // TODO: validate if the Order has been saved already
        return orderRepository.save(orderMapper.toEntity(orderDto));
    }

}
