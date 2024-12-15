package com.order.integrated.service;

import com.order.dto.OrderDto;
import com.order.dto.ProductDto;
import com.order.service.OrderProcessorService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.Set;

@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@SpringBootTest
public class OrderProcessorServiceTest {

    @Autowired
    private OrderProcessorService service;

    @DisplayName("Should process the order and then save it into the database")
    @Test
    public void test1() {
        var orders = Set.of(
                new OrderDto(
                        1L,
                        "CUSTOMER_1",
                        "COMPLETED",
                        Set.of(new ProductDto(1L, "PROD_1", "CAT_1", BigDecimal.valueOf(150.50), 100)),
                        BigDecimal.valueOf(15050).setScale(2, RoundingMode.HALF_UP),
                        LocalDateTime.now()
                )
        );
        var newOrders = service.execute(orders);
        var firstOrder = newOrders.getFirst();

        Assertions.assertNotNull(newOrders);
        Assertions.assertNotNull(firstOrder.getId());
        Assertions.assertNotNull(firstOrder.getTotalPrice());
        Assertions.assertEquals(orders.stream().findFirst().get().getTotalPrice(), firstOrder.getTotalPrice());
        Assertions.assertNotNull(firstOrder.getOrderId());
        Assertions.assertNotNull(firstOrder.getProducts());
    }

}
