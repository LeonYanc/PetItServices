package com.li.petit.service;

import com.li.petit.entity.UrlMapping;
import com.li.petit.repository.UrlMappingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Random;

@Service
public class LongToShortService {
    private HashMap<String, String> longToShortUrlMap = new HashMap<>();
    private static HashMap<String, String> shortToLongUrlMap = new HashMap<>();

    private final UrlMappingRepository urlMappingRepository; // 声明UrlMappingRepository

    @Autowired
    public LongToShortService(UrlMappingRepository urlMappingRepository) {
        this.urlMappingRepository = urlMappingRepository; // 使用构造函数注入
    }

    // 生成随机的短URL
    private String generateShortUrl() {
        String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder shortUrl = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            shortUrl.append(characters.charAt(random.nextInt(characters.length())));
        }
        return shortUrl.toString();
    }

    // 将长URL编码为短URL
    public String encode(String longUrl) {
        if (longToShortUrlMap.containsKey(longUrl)) {
            return longToShortUrlMap.get(longUrl);
        }

        String shortUrl = generateShortUrl(); // 使用 generateShortUrl 生成随机的短URL
        longToShortUrlMap.put(longUrl, shortUrl);
        shortToLongUrlMap.put(shortUrl, longUrl);

        return shortUrl;
    }

    // 将短URL解码为长URL
    public String decode(String shortUrl) {
        // 使用UrlMappingRepository从数据库中查询长URL
        UrlMapping urlMapping = urlMappingRepository.findByShortUrl(shortUrl);

        if (urlMapping != null) {
            return urlMapping.getLongUrl();
        } else {
            // 如果无法找到对应的长URL，可以返回一个默认值或者抛出异常
            // 这里示例返回一个默认的长URL
            return "https://example.com/default-long-url";
        }
    }


    // 添加方法用于根据短URL查找长URL
    public static String getLongByShort(String shortUrl) {
        return shortToLongUrlMap.get(shortUrl);
    }
}




