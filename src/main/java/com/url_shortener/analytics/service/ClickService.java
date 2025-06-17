package com.url_shortener.analytics.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.url_shortener.analytics.dto.TopShortcodeDTO;
import com.url_shortener.analytics.model.Click;
import com.url_shortener.analytics.repository.ClickRepository;

@Service
public class ClickService {
    // @Autowired
    private final ClickRepository clickRepository;

    public ClickService(ClickRepository repo) {
        this.clickRepository = repo;
    }

    private static final Logger logger = LoggerFactory.getLogger(ClickService.class);

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

    public List<Click> getClicksByUser(String userId) {
        return clickRepository.findByUserId(userId);
    }

    public List<TopShortcodeDTO> getMostClickedShortcodes(int limit, String userId) {

        Pageable page = PageRequest.of(0, limit);
        var res = clickRepository.findUserTopShortCodesByClicks(userId, page);
        logger.info("Top {} clicks for user {}: {}", limit, userId, res);
        return res;
    }

    // Analytics methods could go here
}