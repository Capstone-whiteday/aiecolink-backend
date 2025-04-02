package com.whiteday.aiecolink.global.config;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SwaggerController {
    @GetMapping("/test")
    public String test(@RequestParam String str){
        return str;
    }
}
