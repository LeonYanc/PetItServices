/*package com.li.petit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.li.petit.service.LongToShortService; // 导入LongToShortService

@Controller
public class ShortToLongController {
    private final LongToShortService longToShortService; // 使用LongToShortService

    public ShortToLongController(LongToShortService longToShortService) {
        this.longToShortService = longToShortService;
    }

    @GetMapping("/shortToLong")
    public String redirectToLongUrl(@RequestParam("shortUrl") String shortUrl, Model model) {
        String longUrl = longToShortService.decode(shortUrl); // 使用LongToShortService来解码

        if (longUrl != null) {
            model.addAttribute("longUrl", longUrl);
        } else {
            model.addAttribute("error", "Short URL not found");
        }

        return "index";
    }
}
*/
