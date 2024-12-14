package com.order.unit.service;

import com.order.dto.OrderDto;
import com.order.dto.ProductDto;
import com.order.entity.Order;
import com.order.entity.Product;
import com.order.infrastructure.repository.OrderRepository;
import com.order.mapper.OrderMapper;
import com.order.service.SaveOrderService;
import com.order.service.impl.SaveOrderServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Set;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SaveOrderServiceTest {

    private static final String CUSTOMER  = "CUSTOMER_1";
    private static final String PRODUCT = "PROD_1";
    private static final String CATEGORY = "CAT_1";
    private static final String STATUS = "COMPLETED";

    private final OrderMapper mapper =  Mappers.getMapper(OrderMapper.class);
    private final OrderRepository repository = mock(OrderRepository.class);
    private final SaveOrderService service = new SaveOrderServiceImpl(repository, mapper);

    @DisplayName("Should receive an orderDto, convert it to entity and then save to the Db")
    @Test
    public void test1() {
        when(repository.save(any(Order.class)))
                .thenReturn(
                        new Order(
                            1L,
                            UUID.randomUUID().toString(),
                            CUSTOMER,
                                STATUS,
                            Set.of(
                              new Product(1L, UUID.randomUUID().toString(), PRODUCT, BigDecimal.valueOf(250.50), 1)), ZonedDateTime.now()
                            )
                );

        var order = service.save(new OrderDto(1L, CUSTOMER, STATUS, Set.of(new ProductDto(1L, PRODUCT, CATEGORY, BigDecimal.valueOf(250.50), 150))));

        Assertions.assertNotNull(order);
        Assertions.assertNotNull(order.getId());
        Assertions.assertNotNull(order.getProducts());
    }
}
