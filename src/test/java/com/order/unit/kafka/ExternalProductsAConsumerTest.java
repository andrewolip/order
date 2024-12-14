package com.order.unit.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.order.infrastructure.kafka.consumer.ExternalProductAConsumer;
import com.order.service.CalculateTotalProductPriceService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.context.EmbeddedKafka;


// @ExtendWith(MockitoExtension.class)
// @EmbeddedKafka(partitions = 1, topics = {"${spring.topics.consumer}", "${spring.topics.producer}"})
public class ExternalProductsAConsumerTest {

    @Mock
    private CalculateTotalProductPriceService service;

    @Mock
    private KafkaTemplate<String, String> kafkaTemplate;

    @InjectMocks
    private ExternalProductAConsumer consumer;

    public void consume() throws Exception {
    }

}
