package com.url_shortener.analytics.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.url_shortener.analytics.service.ClickService;

@RestController
@RequestMapping("/api/analytics")
public class ClickController {
    @Autowired
    private ClickService clickService;

    @GetMapping("/urls/{shortCode}/clicks")
    public ResponseEntity<Integer> getClickCount(@PathVariable String shortCode){
        return ResponseEntity.ok(clickService.getShortCodeClickCount(shortCode));
    }

}
