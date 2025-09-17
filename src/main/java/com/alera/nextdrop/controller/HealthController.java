package com.alera.nextdrop.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alera.nextdrop.config.SpotifyProperties;

@RestController
public class HealthController {
    private final SpotifyProperties props;

    public HealthController(SpotifyProperties props) {
        this.props = props;
    }

    @GetMapping("/api/ping")
    public String ping() {
        return "ok:" + props.getDefaultCountry();
    }
}

