package com.pranesh.URLshortner.service.impl;

import com.pranesh.URLshortner.dto.UrlRequest;
import com.pranesh.URLshortner.dto.UrlResponse;
import com.pranesh.URLshortner.model.Url;
import com.pranesh.URLshortner.repository.UrlRepository;
import com.pranesh.URLshortner.service.UrlService;
import com.pranesh.URLshortner.util.Base62;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UrlServiceImpl implements UrlService {

    private final UrlRepository urlRepository;

    @Override
    @Transactional
    public UrlResponse shortenUrl(UrlRequest request) {
        // 1. Check if we already have this URL to avoid duplicates
        return urlRepository.findByLongUrl(request.getLongUrl())
                .map(existingUrl -> new UrlResponse(existingUrl.getLongUrl(), existingUrl.getShortKey()))
                .orElseGet(() -> {
                    // 2. If not found, create new
                    Url url = Url.builder()
                            .longUrl(request.getLongUrl())
                            .build();

                    url = urlRepository.save(url); // Now this works because null is allowed!

                    String shortKey = Base62.encode(url.getId());
                    url.setShortKey(shortKey);
                    
                    urlRepository.save(url);
                    return new UrlResponse(url.getLongUrl(), shortKey);
                });
    }

    @Override
    public String getOriginalUrl(String shortKey) {
        return urlRepository.findByShortKey(shortKey)
                .map(Url::getLongUrl)
                .orElseThrow(() -> new RuntimeException("URL not found for key: " + shortKey));
    }
}