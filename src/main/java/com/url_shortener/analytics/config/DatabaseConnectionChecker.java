package com.url_shortener.analytics.config;

import javax.sql.DataSource;
import java.sql.Connection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseConnectionChecker implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(DatabaseConnectionChecker.class);
    private final DataSource dataSource;

    public DatabaseConnectionChecker(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void run(String... args) {
        try (Connection conn = dataSource.getConnection()) {
            logger.info("Database connection successful");
        } catch (Exception e) {
            logger.error("Database connection failed", e);
        }
    }
}