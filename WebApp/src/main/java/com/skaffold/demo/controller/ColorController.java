package com.skaffold.demo.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class ColorController {
    @GetMapping(path = "/getRed")
    public String getRed() {
        return "Red";
    }

    @GetMapping(path = "/checkRed")
    public void checkRed() {

    }

    @GetMapping(path = "/getBlue")
    public String getBlue() {
        return "Blue";
    }
}
