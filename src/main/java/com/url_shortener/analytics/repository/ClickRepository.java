package com.url_shortener.analytics.repository;

import java.util.List;

import com.url_shortener.analytics.dto.TopShortcodeDTO;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.url_shortener.analytics.model.Click;

@Repository
public interface ClickRepository extends JpaRepository<Click, Long> {
    Integer countByShortCode(String shortCode);

    Integer countByShortCodeAndUserId(String shortCode, String ownerId);

    List<Click> findByUserId(String userId);

    // @Query(value = "SELECT short_code, COUNT(*) AS total_clicks " +
    // "FROM clicks " +
    // "WHERE user_id = :userId " +
    // "GROUP BY short_code, user_id " +
    // "ORDER BY total_clicks DESC " +
    // "LIMIT :limit", nativeQuery = true)
    @Query("""
            SELECT new com.url_shortener.analytics.dto.TopShortcodeDTO(c.shortCode, Count(c))
            FROM Click c
            WHERE c.userId = :userId
            GROUP BY c.shortCode
            ORDER BY COUNT(c) DESC
                """)
    List<TopShortcodeDTO> findUserTopShortCodesByClicks(@Param("userId") String userId, Pageable page);

}
