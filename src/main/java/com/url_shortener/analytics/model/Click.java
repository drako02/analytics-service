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
        @Index(name = "idx_short_code", columnList = "short_code")
})
public class Click {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String shortCode;

    @Column(nullable = false)
    private LocalDateTime timestamp;

    @Column(nullable = false, name = "user_id")
    private String userId;

    @Column(name="ip_address", columnDefinition="inet")
    private String ipAddress;

    @Column(name = "duration_ms")
    private Integer redirectionDurationMS;

    private String deviceType;

    private String deviceName;

    private String deviceBrand;

    @Column(name = "device_os")
    private String deviceOS;

    private String browser;

    public static class Builder {
        private final String shortCode;
        private final LocalDateTime timestamp;
        private final String userId;
        private String ipAddress;
        private Integer redirectionDurationMS;
        private String deviceType;
        private String deviceName;
        private String deviceBrand;
        private String deviceOS;
        private String browser;

        public Builder(String shortCode, LocalDateTime timestamp, String userId){
            this.shortCode = shortCode;
            this.timestamp = timestamp;
            this.userId = userId;
        }

        public Builder ipAddress(String ipAddress){
            this.ipAddress = ipAddress;
            return this;
        }
        
        public Builder redirectionDurationMS(Integer redirectionDurationMS){
            this.redirectionDurationMS = redirectionDurationMS;
            return this;
        }
        
        public Builder deviceType(String deviceType){
            this.deviceType = deviceType;
            return this;
        }
        
        public Builder deviceName(String deviceName){
            this.deviceName = deviceName;
            return this;
        }
        
        public Builder deviceBrand(String deviceBrand){
            this.deviceBrand = deviceBrand;
            return this;
        }
        
        public Builder deviceOS(String deviceOS){
            this.deviceOS = deviceOS;
            return this;
        }
        
        public Builder browser(String browser){
            this.browser = browser;
            return this;
        }
        
        public Click build(){
            return new Click(shortCode, userId, timestamp, ipAddress, 
                redirectionDurationMS, deviceType, deviceName, 
                deviceBrand, deviceOS, browser);
        }

    }

    public static Builder builder(String shortCode, String userId, LocalDateTime timestamp ){
        return new Builder(shortCode, timestamp, userId);
    }

    protected Click() {

    }

    public Click(String shortCode) {
        this.shortCode = shortCode;
    }

    public Click(String shortCode, String userId, LocalDateTime timestamp, String ipAddress,
            Integer redirectionDurationMS, String deviceType, String deviceName,
            String deviceBrand, String deviceOS, String browser) {
        this.shortCode = shortCode;
        this.timestamp = timestamp;
        this.userId = userId;
        this.ipAddress = ipAddress;
        this.redirectionDurationMS = redirectionDurationMS;
        this.deviceType = deviceType;
        this.deviceName = deviceName;
        this.deviceBrand = deviceBrand;
        this.deviceOS = deviceOS;
        this.browser = browser;
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

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public Integer getRedirectionDurationMS() {
        return redirectionDurationMS;
    }

    public void setRedirectionDurationMS(Integer redirectionDurationMS) {
        this.redirectionDurationMS = redirectionDurationMS;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getDeviceBrand() {
        return deviceBrand;
    }

    public void setDeviceBrand(String deviceBrand) {
        this.deviceBrand = deviceBrand;
    }

    public String getDeviceOS() {
        return deviceOS;
    }

    public void setDeviceOS(String deviceOS) {
        this.deviceOS = deviceOS;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    @Override
    public String toString() {
        return "Click{" +
                "id=" + id +
                ", shortCode='" + shortCode + '\'' +
                ", timestamp=" + timestamp +
                ", userId='" + userId + '\'' +
                ", ipAddress='" + ipAddress + '\'' +
                ", redirectionDurationMS=" + redirectionDurationMS +
                ", deviceType='" + deviceType + '\'' +
                ", deviceName='" + deviceName + '\'' +
                ", deviceBrand='" + deviceBrand + '\'' +
                ", deviceOS='" + deviceOS + '\'' +
                ", browser='" + browser + '\'' +
                '}';
    }

}
