package com.ahold.allwaysdelivery.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/health-check")
public class HealthCheckController {
    @GetMapping("/ping")
    public String ping() {
        return "{\"status\":\"pass\"}";
    }
}
