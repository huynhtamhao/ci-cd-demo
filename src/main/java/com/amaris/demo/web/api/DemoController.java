package com.amaris.demo.web.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/demo")
public class DemoController {

    @Value("${amaris.demo}")
    String demoText;

    @GetMapping("")
    public String demo() {
        return "Demo Text" + demoText;
    }
}
