package com.calendarproject;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CalendarDemo {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(CalendarDemo.class, args);
    }
}
