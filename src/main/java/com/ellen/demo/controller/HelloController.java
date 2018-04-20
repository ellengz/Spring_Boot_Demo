package com.ellen.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    //@RequestMapping(value = "/greeting", method = RequestMethod.GET)
    @GetMapping(value = "/greeting") // same with the above line
    public String greeting() {
        return "Greeting from Spring Boot";
    }
}
