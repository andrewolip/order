package com.order.unit.service;

import com.order.dto.OrderDto;
import com.order.dto.ProductDto;
import com.order.entity.Order;
import com.order.service.SaveOrderService;
import com.order.service.impl.CalculateTotalProductPriceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collections;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CalculateTotalProductValueServiceTest {

    private static final String CUSTOMER = "CUSTOMER_1";
    private static final String PENDING_STATUS = "PENDING";

    @Mock
    private SaveOrderService saveOrderService;

    @InjectMocks
    private CalculateTotalProductPriceImpl service;

    @DisplayName("Should throw an exception when the orders is null")
    @Test
    public void test1() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            service.calculate(null);
        });

        assertEquals("Orders cannot be null or empty", thrown.getMessage());
    }

    @DisplayName("Should throw an exception when the orders is empty")
    @Test
    public void test2() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            service.calculate(Collections.emptySet());
        });

        assertEquals("Orders cannot be null or empty", thrown.getMessage());
    }

    @DisplayName("Should throw an exception when the product list is null")
    @Test
    public void test3() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            service.calculate(Set.of(
                    new OrderDto(UUID.randomUUID().toString(),1L, CUSTOMER, null, PENDING_STATUS, null, null)
            ));
        });

        assertEquals("Products cannot be null or empty", thrown.getMessage());
    }

    @DisplayName("Should throw an exception when the product list is empty")
    @Test
    public void test4() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            service.calculate(Set.of(
                    new OrderDto(UUID.randomUUID().toString(), 1L, CUSTOMER, Collections.emptySet(), PENDING_STATUS, null, null)
            ));
        });

        assertEquals("Products cannot be null or empty", thrown.getMessage());
    }

    @DisplayName("Should calculate the total product price properly")
    @Test
    public void test5() {
        when(saveOrderService.save(any(OrderDto.class))).thenReturn(any(Order.class));
        var total = new BigDecimal("1357.50").setScale(2, RoundingMode.HALF_UP);
        var orders = service.calculate(
                Set.of(
                        new OrderDto(1L, CUSTOMER, PENDING_STATUS, Set.of(
                                new ProductDto(1L,"Prod1", "Cat1", BigDecimal.valueOf(10), 5),
                                new ProductDto(2L,"Prod2", "Cat1", BigDecimal.valueOf(15.25), 2),
                                new ProductDto(3L,"Prod3", "Cat1", BigDecimal.valueOf(24), 4),
                                new ProductDto(4L,"Prod4", "Cat1", BigDecimal.valueOf(150.50), 2),
                                new ProductDto(5L,"Prod5", "Cat1", BigDecimal.valueOf(100.0), 3),
                                new ProductDto(6L,"Prod6", "Cat1", BigDecimal.valueOf(120), 4),
                                new ProductDto(7L,"Prod7", "Cat1", BigDecimal.valueOf(100), 1)
                        ), total)
                )
        );
        Assertions.assertEquals(orders.getFirst().totalPrice(), total);
    }

}
