package com.order.service.impl;

import com.order.dto.OrderDto;
import com.order.entity.Order;
import com.order.infrastructure.repository.OrderRepository;
import com.order.mapper.OrderMapper;
import com.order.service.SaveOrderService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
    public List<Order> save(Set<OrderDto> orders) {
        return orderRepository.saveAll(orders.stream().map(orderMapper::toEntity).collect(Collectors.toSet()));
    }

}
