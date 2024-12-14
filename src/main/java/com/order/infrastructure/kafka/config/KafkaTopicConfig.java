package com.order.infrastructure.kafka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaTopicConfig {

    @Value("${spring.topics.consumer}")
    private String consumerTopic;

    @Value("${spring.topics.producer}")
    private String producerTopic;

    @Bean
    public NewTopic externalProductATopic() {
        Map<String, String> config = new HashMap<>();
        config.put("max.message.bytes", "20971520");
        return TopicBuilder
                .name(consumerTopic) // TODO: create a variable
                .configs(config)
                .build();
    }

    @Bean
    public NewTopic externalProductBTopic() {
        return TopicBuilder.name(producerTopic).build();
    }

}
