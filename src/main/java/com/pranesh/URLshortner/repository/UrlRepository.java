package com.pranesh.URLshortner.repository;

import com.pranesh.URLshortner.model.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UrlRepository extends JpaRepository<Url, Long> {
    // Look up the original URL when someone uses a short link
    Optional<Url> findByShortKey(String shortKey);
    Optional<Url> findByLongUrl(String longUrl);
}