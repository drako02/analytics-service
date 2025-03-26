package com.url_shortener.analytics.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.url_shortener.analytics.model.Click;
import com.url_shortener.analytics.repository.ClickRepository;

@Service
public class ClickService {
    @Autowired
    private ClickRepository clickRepository;
    
    public Click recordClick(String shortCode) {
        Click click = new Click(shortCode, LocalDateTime.now());
        return clickRepository.save(click);
    }

    public Integer getShortCodeClickCount(String shortCode){
        return clickRepository.countByShortCode(shortCode);
    }
    
    // Analytics methods could go here
}