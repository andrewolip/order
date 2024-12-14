package com.order.infrastructure.kafka.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.order.dto.CalculatedOrderDto;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class ExternalProductBProducer {

    private static final Logger log = LoggerFactory.getLogger(ExternalProductBProducer.class);
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    @Value("${spring.topics.producer}")
    private String topic;

    public ExternalProductBProducer(
            KafkaTemplate<String, String> kafkaTemplate,
            ObjectMapper objectMapper
    ) {
        this.objectMapper = objectMapper;
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(List<CalculatedOrderDto> calculatedOrders) {
        try {
            log.info("Producing a new message {}", calculatedOrders);
            String resultJson = objectMapper.writeValueAsString(calculatedOrders);
            kafkaTemplate.send("externalProductBProducerTopic", resultJson);
        } catch(JsonProcessingException ex) {
            log.error("Error to convert Orders into Kafka message", ex);
        }

    }


}
