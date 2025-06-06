package com.url_shortener.analytics.consumer;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.url_shortener.analytics.model.Click;
import com.url_shortener.analytics.service.ClickService;

import nl.basjes.parse.useragent.UserAgent;
import nl.basjes.parse.useragent.UserAgentAnalyzer;

@Component
public class ClickEventConsumer {

    private static final Logger logger = LoggerFactory.getLogger(ClickEventConsumer.class);

    @Autowired
    private ClickService clickService;

    @Autowired
    private ObjectMapper objectMapper;

    private UserAgentAnalyzer uaa = UserAgentAnalyzer
            .newBuilder()
            .hideMatcherLoadStats()
            .withCache(10000)
            .build();

    @KafkaListener(topics = "redirections")
    public void listen(String message) {
        try {
            JsonNode jsonNode = objectMapper.readTree(message);
            String shortcode = jsonNode.get("short_code").asText();
            String userId = jsonNode.get("user_id").asText();
            String timestamp = jsonNode.get("timestamp").asText();
            String ipAddress = jsonNode.get("client_ip").asText();
            String userAgent = jsonNode.get("user_agent").asText();
            Integer durationMs = jsonNode.get("duration_ms").asInt();

            UserAgent agent = uaa.parse(userAgent);

            String deviceType = agent.getValue(UserAgent.DEVICE_CLASS);
            String deviceName = agent.getValue(UserAgent.DEVICE_NAME);
            String deviceBrand = agent.getValue(UserAgent.DEVICE_BRAND);
            String deviceOS = agent.getValue(UserAgent.OPERATING_SYSTEM_NAME);
            String browser = agent.getValue(UserAgent.AGENT_NAME);

            // Log the parsed user agent details
            logger.info(
                    "Parsed UserAgent - DeviceClass: {}, DeviceName: {}, DeviceBrand: {}, OperatingSystem: {}, Browser: {}",
                    agent.getValue(UserAgent.DEVICE_CLASS),
                    agent.getValue(UserAgent.DEVICE_NAME),
                    agent.getValue(UserAgent.DEVICE_BRAND),
                    agent.getValue(UserAgent.OPERATING_SYSTEM_NAME),
                    agent.getValue(UserAgent.AGENT_NAME));

            logger.info(
                    "Parsed event - shortcode: {}, userId: {}, timestamp: {}, ip: {}, userAgent: {}, durationMs: {}",
                    shortcode, userId, timestamp, ipAddress, userAgent, durationMs);

            LocalDateTime parsedTimestamp = Instant
                    .parse(timestamp) // parses the trailing “Z”
                    .atZone(ZoneOffset.UTC) // interpret in UTC
                    .toLocalDateTime();

            Click click = Click.builder(shortcode, userId, parsedTimestamp)
                    .ipAddress(ipAddress)
                    .redirectionDurationMS(durationMs)
                    .deviceType(deviceType)
                    .deviceName(deviceName)
                    .deviceBrand(deviceBrand)
                    .deviceOS(deviceOS)
                    .browser(browser)
                    .build();

            logger.info("Received message: {}", message);
            // String shortCode = message.trim();
            clickService.recordClick(click);
            logger.info("Processed click for shortCode: {}", shortcode);
        } catch (Exception e) {
            logger.error("Error processing message: {}", message, e);
        }
    }
}
