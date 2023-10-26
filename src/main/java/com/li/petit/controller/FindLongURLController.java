/*package com.li.petit.controller;


import com.li.petit.entity.UrlMapping;
import com.li.petit.repository.UrlMappingRepository;
import com.li.petit.service.LongToShortService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/find-long-url")
@Slf4j
public class FindLongURLController {

    @Autowired
    private LongToShortService longToShortService;

    @Autowired
    private UrlMappingRepository urlMappingRepository; // 使用您的UrlMappingRepository

    @PostMapping
    public String findLongUrl(@RequestParam("shortUrl") String shortUrl, Model model) {
        String shortUrlWithoutSlash = shortUrl.replace("/", "");
        log.info("Short URL without slash: " + shortUrlWithoutSlash);

        String longUrl = longToShortService.getLongByShort(shortUrlWithoutSlash);

        if (longUrl != null) {
            // 在尝试从数据库中获取原始长URL之前检查是否已知道长URL
            model.addAttribute("longUrl", longUrl);
        } else {
            // 尝试从数据库中获取原始长URL
            UrlMapping entity = urlMappingRepository.findByShortUrl(shortUrlWithoutSlash);

            if (entity != null) {
                model.addAttribute("longUrl", entity.getLongUrl());
            } else {
                log.error("Original long URL not found in the database for short URL: " + shortUrlWithoutSlash);
                model.addAttribute("longUrl", "https://example.com/default-long-url");
            }
        }

        return "index";
    }


}*/
package com.li.petit.controller;

import com.li.petit.entity.UrlMapping;
import com.li.petit.repository.UrlMappingRepository;
import com.li.petit.service.LongToShortService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/find-long-url")
@Slf4j
public class FindLongURLController {

    @Autowired
    private LongToShortService longToShortService;

    @Autowired
    private UrlMappingRepository urlMappingRepository;

    @PostMapping
    public RedirectView findLongUrl(@RequestParam("shortUrl") String shortUrl, Model model) {
        String shortUrlWithoutSlash = shortUrl.replace("/", "");
        log.info("Short URL without slash: " + shortUrlWithoutSlash);

        String longUrl = longToShortService.getLongByShort(shortUrlWithoutSlash);

        if (longUrl != null) {
            // 在尝试从数据库中获取原始长URL之前检查是否已知道长URL
            return new RedirectView(longUrl);
        } else {
            // 尝试从数据库中获取原始长URL
            UrlMapping entity = urlMappingRepository.findByShortUrl(shortUrlWithoutSlash);

            if (entity != null) {
                return new RedirectView(entity.getLongUrl());
            } else {
                log.error("Original long URL not found in the database for short URL: " + shortUrlWithoutSlash);
                return new RedirectView("https://example.com/default-long-url");
            }
        }
    }
}




