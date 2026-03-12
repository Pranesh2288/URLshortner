package com.pranesh.URLshortner.controller;

import com.pranesh.URLshortner.dto.UrlRequest;
import com.pranesh.URLshortner.dto.UrlResponse;
import com.pranesh.URLshortner.model.Url;
import com.pranesh.URLshortner.service.UrlService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import com.pranesh.URLshortner.repository.UrlRepository;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequiredArgsConstructor
public class UrlController {

    private final UrlService urlService;
    private final UrlRepository urlRepository;

    @PostMapping("/api/v1/shorten")
    public ResponseEntity<UrlResponse> createShortUrl(@Valid @RequestBody UrlRequest request) {
        UrlResponse response = urlService.shortenUrl(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{shortKey}")
    public RedirectView redirectToOriginal(@PathVariable String shortKey) {
        String originalUrl = urlService.getOriginalUrl(shortKey);
        
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl(originalUrl);
        return redirectView;
    }

    @GetMapping("/api/v1/report/csv")
    public ResponseEntity<String> exportCsv() {
    
    List<Url> urls = urlRepository.findAll(); 
    StringBuilder csvBuilder = new StringBuilder();
    csvBuilder.append("ID,ShortKey,LongUrl\n");
    
    for (Url url : urls) {
        csvBuilder.append(url.getId()).append(",")
                  .append(url.getShortKey()).append(",")
                  .append(url.getLongUrl()).append("\n");
    }

    return ResponseEntity.ok()
            .header("Content-Disposition", "attachment; filename=urls_report.csv")
            .body(csvBuilder.toString());
}
}