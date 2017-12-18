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

import javax.transaction.Transactional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
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
    String getCalendar(@PathVariable String id, Model model){
        Long idLong = new Long(id);
        SimpleCalendar cal = calRepository.findById(idLong).orElse(null);
        model.addAttribute("calendar", cal);
        return "calendarEvents";
    }

    @RequestMapping(value = "/create/{id}", method=RequestMethod.POST)
    String createCalendar(@PathVariable String id){
        return "Creating Calendar of id: "+id;
    }

    @RequestMapping()
    String defaultCalendar(Model model){
        model.addAttribute("calendars", calRepository.findAll());
        return "allCalendars";
    }
    /* Iterable<SimpleCalendar>  defaultCalendar(){
        return calRepository.findAll();
        //return "Default Calendar page (list of calendars?)";
    }*/
}
