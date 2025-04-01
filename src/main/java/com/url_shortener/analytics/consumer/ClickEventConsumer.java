package com.url_shortener.analytics.consumer;

import java.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.url_shortener.analytics.service.ClickService;

@Component
public class ClickEventConsumer {
    
    private static final Logger logger = LoggerFactory.getLogger(ClickEventConsumer.class);
    
    @Autowired
    private ClickService clickService;

    @Autowired
    private ObjectMapper objectMapper;

    @KafkaListener(topics = "redirections")
    public void listen(String message) {
        try {
            JsonNode jsonNode = objectMapper.readTree(message);
            String shortCode = jsonNode.get("shortCode").asText();
            String userId = jsonNode.get("userId").asText();

            logger.info("Received message: {}", message);
            // String shortCode = message.trim();
            clickService.recordClick(shortCode, userId);
            logger.info("Processed click for shortCode: {}", shortCode);
        } catch (Exception e) {
            logger.error("Error processing message: {}", message, e);
        }
    }
}