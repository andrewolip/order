package com.order.integrated.kafka;

import com.order.dto.CalculatedOrderDto;
import com.order.entity.Order;
import com.order.entity.Product;
import com.order.infrastructure.kafka.consumer.ExternalProductAConsumer;
import com.order.infrastructure.kafka.producer.ExternalProductBProducer;
import org.junit.jupiter.api.DisplayName;
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
class ConsumerProducerIntegratedTest {
    private static final String CUSTOMER = "CUSTOMER_1";
    private static final String STATUS = "PENDING";

    @Autowired
    private ExternalProductAConsumer consumer;

    @Autowired
    private ExternalProductBProducer producer;

    @Value("${spring.topics.consumer}")
    private String topic;

    @DisplayName("Should consume a message in ConsumerTopic, calculate the orders and then produce a new message in ProducerTopic")
    @Test
    void testConsume() {

        var orders =  List.of(
                new CalculatedOrderDto(
                        new Order(
                                1L,
                                UUID.randomUUID().toString(),
                                CUSTOMER,
                                STATUS,
                                Set.of(
                                    new Product(1L, UUID.randomUUID().toString(), "Prod1", "Cat1", BigDecimal.valueOf(150), 5),
                                    new Product(2L, UUID.randomUUID().toString(), "Prod2", "Cat2", BigDecimal.valueOf(250), 15)

                                ),
                                ZonedDateTime.now()
                        ),
                        BigDecimal.valueOf(750.00)
                )
        );

        // TODO: extract to separate json file
        String message = """
                [
                  {
                  	"customer": "Customer1",
                  	"status": "PENDING",
                  	"products": [
                  		{
                  			"name": "Food",
                  			"category": "Book",
                  			"price": 100,
                  			"quantity": 10
                  		},
                  		{
                  			"name": "Java",
                  			"category": "Book",
                  			"price": 10,
                  			"quantity": 10
                  		}
                  	]
                   },
                   {
                  	"customer": "Customer2",
                  	"status": "PENDING",
                  	"products": [
                  		{
                  			"name": "Food",
                  			"category": "Book",
                  			"price": 100,
                  			"quantity": 10
                  		},
                  		{
                  			"name": "Java",
                  			"category": "Book",
                  			"price": 10,
                  			"quantity": 10
                  		}
                  	]
                   }
                 \s
                ]
        """;

        consumer.consume(message);
    }

}
