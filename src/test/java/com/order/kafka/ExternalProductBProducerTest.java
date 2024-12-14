package com.order.kafka;

import com.order.dto.CalculatedOrderDto;
import com.order.entity.Order;
import com.order.entity.Product;
import com.order.infrastructure.kafka.producer.ExternalProductBProducer;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

//TODO: NOT WORKING YET
public class ExternalProductBProducerTest {

    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private ExternalProductBProducer externalProductBProducer;

    @Value("${spring.topics.producer}")
    private String topic;

    @Test
    void testSendMessage() {
        var message = "";
        Mockito.doNothing().when(kafkaTemplate).send(topic, message);

        var calculatedProducts = List.of(
                new CalculatedOrderDto(
                        new Order(
                                UUID.randomUUID().toString(),
                                "CUSTOMER_1",
                                "CALCULATED",
                                Set.of(new Product(UUID.randomUUID().toString(), "CAT_1", "PROD_1", BigDecimal.valueOf(1500.45), 1)),
                                ZonedDateTime.now()
                        ),
                        BigDecimal.valueOf(1500.45))
        );

        externalProductBProducer.sendMessage(calculatedProducts);

        Mockito.verify(kafkaTemplate, Mockito.times(1)).send(topic, message);
    }

}


