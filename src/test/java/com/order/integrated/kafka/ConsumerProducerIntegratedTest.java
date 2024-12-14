package com.order.integrated.kafka;

import com.order.infrastructure.kafka.consumer.ExternalProductAConsumer;
import com.order.infrastructure.kafka.producer.ExternalProductBProducer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;

@SpringBootTest
@EmbeddedKafka(
        partitions = 1,
        brokerProperties = { "listeners=PLAINTEXT://localhost:9092", "port=9092" }
)
class ConsumerProducerIntegratedTest {

    @Autowired
    private ExternalProductAConsumer consumer;

    @Autowired
    private ExternalProductBProducer producer;

    @DisplayName("Should consume a message in ConsumerTopic, calculate the orders and then produce a new message in ProducerTopic")
    @Test
    void testConsume() {

        // TODO: extract to separate json file
        String message = """
                [
                  {
                    "orderId": 1,
                  	"customer": "Customer1",
                  	"status": "PENDING",
                  	"products": [
                  		{
                  		    "productId": 1,
                  			"name": "Food",
                  			"category": "Book",
                  			"price": 100,
                  			"quantity": 10
                  		},
                  		{
                  		    "productId": 2,
                  			"name": "Java",
                  			"category": "Book",
                  			"price": 10,
                  			"quantity": 10
                  		}
                  	]
                   },
                   {
                    "orderId": 2,
                  	"customer": "Customer2",
                  	"status": "PENDING",
                  	"products": [
                  		{
                  		    "productId": 1,
                  			"name": "Food",
                  			"category": "Book",
                  			"price": 100,
                  			"quantity": 10
                  		},
                  		{
                  		    "productId": 2,
                  			"name": "Java",
                  			"category": "Book",
                  			"price": 10,
                  			"quantity": 10
                  		}
                  	]
                   }
                ]
        """;

        consumer.consume(message);
    }

}
