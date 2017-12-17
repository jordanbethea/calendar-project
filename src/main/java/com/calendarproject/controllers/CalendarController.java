package com.calendarproject.controllers;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;
import com.calendarproject.repositories.*;
import com.calendarproject.model.*;

@RestController
@RequestMapping("/calendar")
public class CalendarController {

    private final CalendarRepository calRepository;
    private final EventRepository eventRepository;

    @Autowired
    public CalendarController(CalendarRepository calRepository, EventRepository eventRepository){
        this.calRepository = calRepository;
        this.eventRepository = eventRepository;
    }

    @RequestMapping(value = "/fetch/{id}", method=RequestMethod.GET)
    SimpleCalendar getCalendar(@PathVariable String id){
        Long idLong = new Long(id);
        return calRepository.findById(idLong).orElse(null);
    }

    @RequestMapping(value = "/create/{id}", method=RequestMethod.POST)
    String createCalendar(@PathVariable String id){
        return "Creating Calendar of id: "+id;
    }

    @RequestMapping()
    Iterable<SimpleCalendar>  defaultCalendar(){
        return calRepository.findAll();
        //return "Default Calendar page (list of calendars?)";
    }
}
