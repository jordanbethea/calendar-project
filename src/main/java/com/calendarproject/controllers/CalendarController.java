package com.calendarproject.controllers;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.format.annotation.DateTimeFormat;
import com.calendarproject.repositories.*;
import com.calendarproject.model.*;

import javax.transaction.Transactional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import java.util.Date;
import java.util.Set;
import java.util.HashSet;
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

    @RequestMapping(value = "/new", method=RequestMethod.GET)
    String newCalendar(Model model){
        return "newCalendar";
    }

    @RequestMapping(value = "/create", method=RequestMethod.POST)
    String createCalendar(
            @RequestParam(value="name") String name,
            @RequestParam(value="user") String user,
            Model model
    ){
        SimpleCalendar cal = new SimpleCalendar(name, user);
        calRepository.save(cal);
        return defaultCalendar(model); //Should be an actual redirect to prevent double submits
    }

    @RequestMapping(value="{id}/event/new",method=RequestMethod.GET)
    String newEvent(
            @PathVariable String id,
            Model model
    ){
        model.addAttribute("calID", id);
        return "newEvent";
    }

    @RequestMapping(value="{calID}/event/create",method=RequestMethod.POST)
    String createEvent(
            @PathVariable String calID,
            @RequestParam(value="title") String title,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam(value="eventDate") Date eventDate,
            @RequestParam(value="location") String location,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam(value="reminderDate") Date reminderDate,
            @RequestParam(value="guest") String guest,
            Model model
    ){
                Long idLong = new Long(calID);
                SimpleCalendar cal = calRepository.findById(idLong).orElse(null);
                if(cal != null){
                    Set<String> guests = new HashSet<String>();
                    guests.add(guest);
                    CalendarEvent event = new CalendarEvent(cal, title, eventDate, location,guests, reminderDate, false);
                    eventRepository.save(event);
                }
                return getCalendar(calID, model);
    }


    @RequestMapping()
    String defaultCalendar(Model model){
        model.addAttribute("calendars", calRepository.findAll());
        return "allCalendars";
    }

}
