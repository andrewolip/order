package com.order.kafka;

import com.order.infrastructure.kafka.consumer.ExternalProductAConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;

//TODO: NOT WORKING YET
public class ExternalProductAConsumerTest {

    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private ExternalProductAConsumer externalProductAConsumer;


}
