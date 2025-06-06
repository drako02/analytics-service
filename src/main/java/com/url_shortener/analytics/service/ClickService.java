package com.url_shortener.analytics.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.url_shortener.analytics.model.Click;
import com.url_shortener.analytics.repository.ClickRepository;

@Service
public class ClickService {
    @Autowired
    private ClickRepository clickRepository;

    public Click recordClick(Click click) {
        // Click click = new Click(shortCode, userId, LocalDateTime.now());
        // Click click = Click.builder(shortCode, userId, null).build();
        return clickRepository.save(click);
    }

    public Integer getShortCodeClickCount(String shortCode) {
        return clickRepository.countByShortCode(shortCode);
    }

    public Integer getUserShortCodeCount(String shortCode, String userId) {
        return clickRepository.countByShortCodeAndUserId(shortCode, userId);
    }

    public List<Click> getClicksByUser(String userId){
        return clickRepository.findByUserId(userId);
    }

    // Analytics methods could go here
}