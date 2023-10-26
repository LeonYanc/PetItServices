package com.li.petit.controller;

import com.li.petit.entity.UrlMapping;
import com.li.petit.repository.UrlMappingRepository;
import com.li.petit.service.LongToShortService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Api(tags = "URL Shortener API")
@Slf4j
public class LongToShortController {

    private final LongToShortService longToShortService;
    private final UrlMappingRepository urlMappingRepository;

    @Autowired
    public LongToShortController(LongToShortService longToShortService, UrlMappingRepository urlMappingRepository) {
        this.longToShortService = longToShortService;
        this.urlMappingRepository = urlMappingRepository;
    }

    @PostMapping("/shorten")
    @ApiOperation(value = "缩短网址")
    public String shortenUrl(@RequestParam("longUrl") String longUrl, Model model) {
        String shortUrl = longToShortService.encode(longUrl);

        UrlMapping urlMapping = new UrlMapping();
        urlMapping.setShortUrl(shortUrl);
        urlMapping.setLongUrl(longUrl);
        urlMapping.setUsername("url_user");

        urlMappingRepository.save(urlMapping);

        model.addAttribute("shortUrl", shortUrl);
        log.info("Short URL: " + shortUrl);

        return "index"; // 返回视图名称，用于显示生成的短URL
    }
}
