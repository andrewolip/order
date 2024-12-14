package com.order.mapper;

import com.order.dto.OrderDto;
import com.order.entity.Order;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface OrderMapper {
    Order toEntity(OrderDto dto);
}
