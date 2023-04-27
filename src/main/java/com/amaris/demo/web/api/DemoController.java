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
        String text = "";
        if (text.equals("A"))
            System.out.println("A");
        if (text.equals("B"))
            System.out.println("B");
        if (text.equals("C"))
            System.out.println("C");
        if (text.equals(""))
            System.out.println("");
        return "Demo Text: " + demoText;
    }
}
