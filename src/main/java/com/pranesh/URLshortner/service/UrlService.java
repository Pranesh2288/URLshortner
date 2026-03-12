package com.pranesh.URLshortner.service;

import com.pranesh.URLshortner.dto.UrlRequest;
import com.pranesh.URLshortner.dto.UrlResponse;

public interface UrlService {
    UrlResponse shortenUrl(UrlRequest request);
    String getOriginalUrl(String shortKey);
}