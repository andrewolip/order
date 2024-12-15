package com.order.infrastructure.kafka.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.order.dto.OrderDto;
import com.order.infrastructure.kafka.producer.ExternalProductBProducer;
import com.order.service.CalculateTotalProductPriceService;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Slf4j
@Component
public class ExternalProductAConsumer {

    private static final Logger log = LoggerFactory.getLogger(ExternalProductAConsumer.class);

    private final ObjectMapper objectMapper;
    private final CalculateTotalProductPriceService service;
    private final ExternalProductBProducer producer;

    @Value("${spring.kafka.consumer.group-id}")
    private String groupId;

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapAddress;

    @Value("${spring.topics.consumer}")
    private String topic;

    public ExternalProductAConsumer(
            ExternalProductBProducer producer,
            CalculateTotalProductPriceService service,
            ObjectMapper objectMapper
    ) {
        this.producer = producer;
        this.service = service;
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = "${spring.topics.consumer}", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(String message) {
        try {
            log.info("Consuming a new message {}", message);
            Set<OrderDto> orders = objectMapper.readValue(message, new TypeReference<HashSet<OrderDto>>() {});
            var calculatedOrders = service.calculate(orders);
            producer.sendMessage(calculatedOrders);
        } catch (JsonProcessingException ex) {
            log.error("Error to convert kafka message into object", ex);
        }
    }

    public DefaultKafkaConsumerFactory<String, String> consumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.MAX_PARTITION_FETCH_BYTES_CONFIG, "20971520");
        props.put(ConsumerConfig.FETCH_MAX_BYTES_CONFIG, "20971520");
        return new DefaultKafkaConsumerFactory<>(props);
    }

}
