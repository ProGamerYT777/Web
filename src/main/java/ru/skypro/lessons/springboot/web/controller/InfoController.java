package ru.skypro.lessons.springboot.web.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InfoController {
    @Value("${app.env}")
    private String profile;

    @GetMapping("/appInfo")
    public String appInfo() {
        return profile;
    }

}
