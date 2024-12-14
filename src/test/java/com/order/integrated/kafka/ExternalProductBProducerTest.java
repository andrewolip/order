package com.order.integrated.kafka;

import com.order.dto.CalculatedOrderDto;
import com.order.entity.Order;
import com.order.entity.Product;
import com.order.infrastructure.kafka.producer.ExternalProductBProducer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@SpringBootTest
@EmbeddedKafka(
        partitions = 1,
        brokerProperties = { "listeners=PLAINTEXT://localhost:9092", "port=9092" }
)
public class ExternalProductBProducerTest {

    @Autowired
    private ExternalProductBProducer producer;

    @Value("${spring.topics.consumer}")
    private String topic;

    @Test
    void testSendMessage() {
        var orders = List.of(
                new CalculatedOrderDto(
                        new Order(
                                UUID.randomUUID().toString(),
                                1L,
                                "CUSTOMER_1",
                                "COMPLETED",
                                Set.of(new Product(UUID.randomUUID().toString(), 1L, "CAT_1", "PROD_1", BigDecimal.valueOf(1500.45), 1)),
                                ZonedDateTime.now()
                        ),
                        BigDecimal.valueOf(1500.45))
        );
        producer.sendMessage(orders);

    }

}


