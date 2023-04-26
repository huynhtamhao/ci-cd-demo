package com.amaris.demo.web.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/heath-check")
public class HealthCheckController {
    @GetMapping("")
    public String heathCheck() {
        return "It's works";
    }
}
