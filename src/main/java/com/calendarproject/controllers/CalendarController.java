package com.calendarproject.controllers;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/calendar")
public class CalendarController {
    @RequestMapping(value = "/fetch/{id}", method=RequestMethod.GET)
    String getCalendar(@PathVariable String id){
        return "Returning Calendar of id: "+id;
    }

    @RequestMapping(value = "/create/{id}", method=RequestMethod.POST)
    String createCalendar(@PathVariable String id){
        return "Creating Calendar of id: "+id;
    }

    @RequestMapping()
    public String defaultCalendar(){
        return "Default Calendar page (list of calendars?)";
    }
}
