package com.calendarproject.controllers;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.servlet.view.RedirectView;
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
    /*
        Calendar Methods
     */
    @RequestMapping()
    String defaultCalendar(Model model){
        model.addAttribute("calendars", calRepository.findAll());
        return "allCalendars";
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
    RedirectView createCalendar(
            @RequestParam(value="name") String name,
            @RequestParam(value="user") String user,
            Model model
    ){
        SimpleCalendar cal = new SimpleCalendar(name, user);
        calRepository.save(cal);
        //return defaultCalendar(model); //Should be an actual redirect to prevent double submits
        return new RedirectView("/calendar");
    }
    @RequestMapping(value = "/{id}/delete", method=RequestMethod.POST) //would use the delete request type, but not supported by form
    RedirectView deleteCalendar(@PathVariable String id, Model model){
                Long idLong = new Long(id);
                calRepository.deleteById(idLong);
                return new RedirectView("/calendar");
    }

    @RequestMapping(value = "/{id}/edit", method=RequestMethod.GET)
    String editCalendar(@PathVariable String id, Model model){
        long idLong = new Long(id);
        SimpleCalendar cal = calRepository.findById(idLong).orElse(null);
        model.addAttribute("calendar",cal);
        return "editCalendar";
    }

    @RequestMapping(value="/{id}/edit", method=RequestMethod.POST)
    RedirectView updateCalendar(@PathVariable String id, @RequestParam String name, @RequestParam String user, Model model){
        long idLong = new Long(id);
        SimpleCalendar cal = calRepository.findById(idLong).orElse(null);
        cal.setName(name);
        cal.setUser(user);
        calRepository.save(cal);
        return new RedirectView("/calendar/fetch/"+id);
    }
    /*
        Event Methods
     */
    @RequestMapping(value="{id}/event/new",method=RequestMethod.GET)
    String newEvent(
            @PathVariable String id,
            Model model
    ){
        model.addAttribute("calID", id);
        return "newEvent";
    }

    @RequestMapping(value="{calID}/event/create",method=RequestMethod.POST)
    RedirectView createEvent(
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
                //return getCalendar(calID, model);
                return new RedirectView("/calendar/fetch/"+calID);
    }

    @RequestMapping(value="{calID}/event/{evID}/delete",method=RequestMethod.POST)
    RedirectView deleteEvent(@PathVariable String calID, @PathVariable String evID, Model model){
        Long idLong = new Long(evID); //for some reason my ID's are counting from the same pool, so don't need cal ID
        eventRepository.deleteById(idLong);
        return new RedirectView("/calendar/fetch/"+calID);
    }

    @RequestMapping(value="{calID}/event/{evID}/edit", method=RequestMethod.GET)
    String editEvent(@PathVariable String calID, @PathVariable String evID, Model model){
        Long idLong = new Long(evID);
        CalendarEvent event = eventRepository.findById(idLong).orElse(null);
        model.addAttribute("event", event);
        model.addAttribute("calID", calID);
        return "editEvent";
    }

    @RequestMapping(value="{calID}/event/{evID}/edit", method=RequestMethod.POST)
    RedirectView updateEvent(@PathVariable String calID, @PathVariable String evID,
                             @RequestParam(value="title") String title,
                             @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam(value="eventDate") Date eventDate,
                             @RequestParam(value="location") String location,
                             @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam(value="reminderDate") Date reminderDate,
                             @RequestParam(value="guest") String guest,
                             Model model){
        Long idLong = new Long(evID);
        CalendarEvent event = eventRepository.findById(idLong).orElse(null);
        event.setTitle(title);
        event.setEventDate(eventDate);
        event.setLocation(location);
        event.setReminderDate(reminderDate);
        Set<String> guests = new HashSet<String>();
        guests.add(guest);
        event.setGuestList(guests);
        eventRepository.save(event);

        return new RedirectView("/calendar/fetch/"+calID);
    }



}
