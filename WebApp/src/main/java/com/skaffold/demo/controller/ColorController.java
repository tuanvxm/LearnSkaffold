package com.skaffold.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ColorController {
    @GetMapping(path = "/getRed")
    public String getRed() {
        return "Red";
    }

    @GetMapping(path = "/getGreen")
    public String getGreen() {
        return "Green";
    }

    @GetMapping(path = "/getPurple")
    public String getBlue() {
        return "Blue";
    }
}
