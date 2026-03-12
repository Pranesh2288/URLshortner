package com.pranesh.URLshortner.service.impl;

import com.pranesh.URLshortner.dto.UrlRequest;
import com.pranesh.URLshortner.dto.UrlResponse;
import com.pranesh.URLshortner.model.Url;
import com.pranesh.URLshortner.repository.UrlRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class UrlServiceImplTest {

    @Mock
    private UrlRepository urlRepository;

    @InjectMocks
    private UrlServiceImpl urlService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testShortenUrl_NewLink() {
        // 1. Arrange (Prepare your fake data)
        UrlRequest request = new UrlRequest();
        request.setLongUrl("https://apple.com");

        Url savedUrl = Url.builder().id(100L).longUrl("https://apple.com").build();

        // Tell Mockito: when save is called, return our savedUrl with ID 100
        when(urlRepository.findByLongUrl(anyString())).thenReturn(Optional.empty());
        when(urlRepository.save(any(Url.class))).thenReturn(savedUrl);

        // 2. Act (Call the actual method)
        UrlResponse response = urlService.shortenUrl(request);

        // 3. Assert (Check if the result is what we expect)
        assertNotNull(response);
        assertEquals("https://apple.com", response.getLongUrl());
        assertNotNull(response.getShortUrl());
        
        // Verify that the repository's save method was called twice
        verify(urlRepository, times(2)).save(any(Url.class));
    }
}