package com.li.petit.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Controller
public class UrlShortenerController {
    private Map<String, String> urlMappings = new HashMap<>();
    private final String baseURL = "http://localhost:8080/";

    @GetMapping("/")
    public String showUrlShortenerForm(Model model) {
        model.addAttribute("shortUrl", null);
        return "index";
    }

    @PostMapping("/shorten")
    public String shortenUrl(@RequestParam("longUrl") String longUrl, Model model) {
        String shortUrl = generateShortUrl();
        urlMappings.put(shortUrl, longUrl);
        model.addAttribute("shortUrl", baseURL + shortUrl);
        return "index";
    }

    @GetMapping("/{shortUrl}")
    public ResponseEntity<Object> redirectToLongUrl(@PathVariable String shortUrl) {
        String longUrl = urlMappings.get(shortUrl);
        if (longUrl != null) {
            return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(longUrl)).build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    private String generateShortUrl() {
        // 生成随机的短URL，可以根据需要进行优化
        String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder shortUrl = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            shortUrl.append(characters.charAt(random.nextInt(characters.length())));
        }
        return shortUrl.toString();
    }
}
