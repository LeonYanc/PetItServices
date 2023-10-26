package com.li.petit.util;

import java.util.HashMap;

public class Codec {
    private HashMap<Long, String> idToUrl = new HashMap<>();
    private HashMap<String, Long> urlToId = new HashMap<>();
    private long nextId = 0;

    // Encodes a URL to a shortened URL.

    public String encode(String longUrl) {
        if (urlToId.containsKey(longUrl)) {
            return idToShortURL(urlToId.get(longUrl));
        } else {
            urlToId.put(longUrl, nextId);
            idToUrl.put(nextId, longUrl);
            nextId++;
            return idToShortURL(nextId - 1); // 修改此行，返回正确的短网址
        }
    }



    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        long id = shortURLtoID(shortUrl);
        return idToUrl.get(id);
    }

    private String idToShortURL(long n) {
        // Map to store 62 possible characters
        char[] map = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();

        StringBuilder shorturl = new StringBuilder();

        // Convert given integer id to a base 62 number
        while (n > 0) {
            shorturl.append(map[(int) (n % 62)]);
            n /= 62;
        }

        // Reverse shortURL to complete base conversion
        return shorturl.reverse().toString();
    }

    private long shortURLtoID(String shortURL) {
        long id = 0;

        for (int i = 0; i < shortURL.length(); i++) {
            char c = shortURL.charAt(i);
            if ('a' <= c && c <= 'z') {
                id = id * 62 + c - 'a';
            }
            if ('A' <= c && c <= 'Z') {
                id = id * 62 + c - 'A' + 26;
            }
            if ('0' <= c && c <= '9') {
                id = id * 62 + c - '0' + 52;
            }
        }

        return id;
    }
}

