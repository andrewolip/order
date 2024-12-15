package com.order.unit.service;

import com.order.dto.OrderDto;
import com.order.dto.ProductDto;
import com.order.entity.Order;
import com.order.entity.Product;
import com.order.infrastructure.repository.OrderRepository;
import com.order.mapper.OrderMapper;
import com.order.service.CalculateTotalProductPriceService;
import com.order.service.OrderProcessorService;
import com.order.service.SaveOrderService;
import com.order.service.impl.CalculateTotalProductPriceImpl;
import com.order.service.impl.OrderProcessorServiceImpl;
import com.order.service.impl.SaveOrderServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class OrderProcessorServiceTest {

    private static final String CUSTOMER  = "CUSTOMER_1";
    private static final String PRODUCT = "PROD_1";
    private static final String CATEGORY = "CAT_1";
    private static final String STATUS = "COMPLETED";

    private final OrderMapper mapper =  Mappers.getMapper(OrderMapper.class);

    private final CalculateTotalProductPriceService calculateService = mock(CalculateTotalProductPriceImpl.class);
    private final SaveOrderService saveOrderService = mock(SaveOrderServiceImpl.class);

    private final OrderProcessorService service = new OrderProcessorServiceImpl(calculateService, saveOrderService, mapper);

    @DisplayName("Should receive an orderDto, convert it to entity and then save to the Db")
    @Test
    public void test1() {
        when(calculateService.calculate(anySet()))
                .thenReturn(
                        Set.of(
                            new OrderDto(
                            1L,
                            CUSTOMER,
                            STATUS,
                            Set.of(new ProductDto(1L, PRODUCT, "CAT_1", BigDecimal.valueOf(250.50), 1)),
                            BigDecimal.valueOf(250.50).setScale(2, RoundingMode.HALF_UP),
                            LocalDateTime.now())
                        )
                );

        when(saveOrderService.save(anySet())).thenReturn(List.of(
                new Order(
                        UUID.randomUUID().toString(),
                        1L,
                        CUSTOMER,
                        STATUS,
                        Set.of(new Product(UUID.randomUUID().toString(), 1L, PRODUCT, "CAT_1", BigDecimal.valueOf(250.50), 1)),
                        LocalDateTime.now()
                )
        ));

        var orders = service.execute(Set.of( new OrderDto(
                1L,
                CUSTOMER,
                STATUS,
                Set.of(new ProductDto(1L, PRODUCT, CATEGORY, BigDecimal.valueOf(250.50), 150)),
                BigDecimal.valueOf(250.50),
                LocalDateTime.now())));

        Assertions.assertNotNull(orders);
        Assertions.assertNotNull(orders.getFirst().getId());
        Assertions.assertNotNull(orders.getFirst().getProducts());
    }
}
