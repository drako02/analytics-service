package com.url_shortener.analytics.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.url_shortener.analytics.model.Click;

public interface ClickRepository  extends JpaRepository<Click, Long>{
    Integer countByShortCode(String shortCode);


}
