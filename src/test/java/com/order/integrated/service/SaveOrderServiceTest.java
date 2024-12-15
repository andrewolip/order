package com.order.integrated.service;

import com.order.dto.OrderDto;
import com.order.dto.ProductDto;
import com.order.service.SaveOrderService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@SpringBootTest
public class SaveOrderServiceTest {

    @Autowired
    private SaveOrderService service;

    @DisplayName("Should convert an orderDto to entity and then save it into the database")
    @Test
    public void test1() {
        var order = new OrderDto(
                1L,
                "CUSTOMER_1",
                "COMPLETED",
                Set.of(new ProductDto(1L, "PROD_1", "CAT_1", BigDecimal.valueOf(150.50), 100)),
                BigDecimal.valueOf(15050),
                LocalDateTime.now()
        );
        var newOrder = service.save(order);

        Assertions.assertNotNull(newOrder);
        Assertions.assertNotNull(newOrder.getId());
        Assertions.assertNotNull(newOrder.getTotalPrice());
        Assertions.assertEquals(order.getTotalPrice(), newOrder.getTotalPrice());
        Assertions.assertNotNull(newOrder.getOrderId());
        Assertions.assertNotNull(newOrder.getProducts());
    }

}
