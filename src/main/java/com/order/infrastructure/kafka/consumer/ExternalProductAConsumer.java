package com.order.infrastructure.kafka.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.order.dto.OrderDto;
import com.order.infrastructure.kafka.producer.ExternalProductBProducer;
import com.order.service.OrderProcessorService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Component
public class ExternalProductAConsumer {

    private static final Logger log = LoggerFactory.getLogger(ExternalProductAConsumer.class);

    private final ObjectMapper objectMapper;
    private final ExternalProductBProducer producer;
    private final OrderProcessorService orderProcessorService;

    @Value("${spring.kafka.consumer.group-id}")
    private String groupId;

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapAddress;

    @Value("${spring.topics.consumer}")
    private String topic;

    public ExternalProductAConsumer(
            ExternalProductBProducer producer,
            OrderProcessorService orderProcessorService,
            ObjectMapper objectMapper
    ) {
        this.producer = producer;
        this.orderProcessorService = orderProcessorService;
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = "${spring.topics.consumer}", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(String message) {
        try {
            log.info("Consuming a new message {}", message);
            Set<OrderDto> orders = objectMapper.readValue(message, new TypeReference<HashSet<OrderDto>>() {});
            var newOrders = orderProcessorService.execute(orders);
            producer.sendMessage(newOrders);
        } catch (JsonProcessingException ex) {
            log.error("Error to convert kafka message into object", ex);
        }
    }

}
