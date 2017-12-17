package com.calendarproject;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.calendarproject.repositories.*;
import com.calendarproject.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;


@SpringBootApplication
public class CalendarDemo {

    private static final Logger log = LoggerFactory.getLogger(CalendarDemo.class);

    public static void main(String[] args) throws Exception {
        SpringApplication.run(CalendarDemo.class, args);
    }

    @Bean
    public CommandLineRunner persistenceTest(CalendarRepository repository, EventRepository eRepository) {
        return (args) -> {

            repository.save(new SimpleCalendar("Space Events", "Han Solo"));
            repository.save(new SimpleCalendar("Archaeology Events", "Indiana Jones"));

            log.info("Saved Customers, now loading them:");
            SimpleCalendar cal1 = repository.findById(1L).orElse(null);
            if(cal1 == null){ log.info("First retrieval was null");}
            else{ log.info(cal1.toString());}

            SimpleCalendar cal2 = repository.findById(2L).orElse(null);
            if(cal2 == null){ log.info("Second retrieval was null");}
            else{ log.info(cal2.toString());}

            eRepository.save(new CalendarEvent(cal1,"Falcon Tuneup", new Date(), "Mos Eisley",null,
                    new Date(), false));
            log.info("Saved an event, now loading:");
            CalendarEvent event1 = eRepository.findById(3L).orElse(null);
            if(event1 == null){ log.info("Event was null"); }
            else{ log.info(event1.toString()); }

            event1.addGuest("Leia");
            event1.addGuest("Chewie");
            eRepository.save(event1);
            log.info("Saved with new guest list");
            CalendarEvent event2 = eRepository.findById(3L).orElse(null);
            if(event2 == null){ log.info("Updated Event was null"); }
            else{ log.info(event2.toString()); }

            SimpleCalendar cal3 = repository.findById(1L).orElse(null);
            List<CalendarEvent> events = cal3.getEvents();
            log.info("retrieving event info from calendar:");
            log.info(events.toString());

        };
    }
}