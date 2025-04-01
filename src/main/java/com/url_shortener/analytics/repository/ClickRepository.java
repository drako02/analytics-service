package com.url_shortener.analytics.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.url_shortener.analytics.model.Click;

public interface ClickRepository extends JpaRepository<Click, Long> {
    Integer countByShortCode(String shortCode);

    Integer countByShortCodeAndUserId(String shortCode, String ownerId);

    List<Click> findByUserId(String userId);

}
