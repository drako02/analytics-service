package com.url_shortener.analytics.dto;

import org.springframework.context.annotation.Configuration;

public class TopShortcodeDTO {
    private final String shortCode;
    private final Long totalClicks;

    public TopShortcodeDTO(String shortCode, Long totalClicks){
        this.shortCode = shortCode;
        this.totalClicks = totalClicks;
    }

    public String getShortCode() { return shortCode; }
    public Long getTotalClicks() { return totalClicks; }

}
