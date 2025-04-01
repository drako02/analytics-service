package com.url_shortener.analytics.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.Index;

@Entity
@Table(name = "clicks", indexes = {
        @Index(name = "idx_short_code", columnList = "shortCode")
})
public class Click {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String shortCode;

    @Column(nullable = false)
    private LocalDateTime timestamp;

    @Column(name = "user_id")
    private String userId;

    public Click() {

    }

    public Click(String shortCode) {
        this.shortCode = shortCode;
    }

    public Click(String shortCode, String userId, LocalDateTime timestamp) {
        this.shortCode = shortCode;
        this.timestamp = timestamp;
        this.userId = userId;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getShortCode() {
        return shortCode;
    }

    public void setShortCode(String shortCode) {
        this.shortCode = shortCode;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Click{" +
                "id=" + id +
                ", shortCode='" + shortCode + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }

}
