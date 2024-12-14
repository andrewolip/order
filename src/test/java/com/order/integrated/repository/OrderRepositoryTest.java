package com.order.integrated.repository;

import com.order.entity.Order;
import com.order.entity.Product;
import com.order.infrastructure.repository.OrderRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.util.Set;

@ExtendWith(MockitoExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class OrderRepositoryTest {

    private static final String CUSTOMER = "CUSTOMER_1";
    private static final String STATUS = "PENDING";

    @Autowired
    private OrderRepository repository;

    @AfterEach
    void beforeEach() {
        repository.deleteAll();
    }

    @DisplayName("Should save an order and return the saved one with products")
    @Test
    public void test1() {
        var products = Set.of(
                new Product(1L,"CAT1", CUSTOMER, BigDecimal.valueOf(15.0), 250),
                new Product(2L,"CAT2", CUSTOMER, BigDecimal.valueOf(20.0), 6),
                new Product(3L, "CAT3", CUSTOMER, BigDecimal.valueOf(120.0), 9),
                new Product(4L, "CAT4", CUSTOMER, BigDecimal.valueOf(110.0), 300),
                new Product(5L,"CAT5", CUSTOMER, BigDecimal.valueOf(100.0), 10)
        );
        var order = new Order(1L, CUSTOMER, STATUS, products);
        var savedOrder = repository.save(order);
        Assertions.assertNotNull(savedOrder);
        Assertions.assertNotNull(savedOrder.getId());
        Assertions.assertNotNull(savedOrder.getOrderId());
        Assertions.assertFalse(savedOrder.getProducts().isEmpty());
    }

}
