package com.calendarproject.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/")
public class DefaultController {
    @RequestMapping()
    String defaultMethod(){
        return "This is the default calendar management page";
    }
}
