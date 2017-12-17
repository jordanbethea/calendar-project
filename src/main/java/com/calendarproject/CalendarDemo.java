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

            SimpleCalendar testCal1 = new SimpleCalendar("Space Events", "Han Solo");
            CalendarEvent testCal1Ev1 = new CalendarEvent(testCal1, "Falcon Tuneup", new Date(), "Mos Eisley",
                    null, new Date(), false);
            testCal1Ev1.addGuest("Leia");
            testCal1Ev1.addGuest("Chewie");

            repository.save(testCal1);
            eRepository.save(testCal1Ev1);

            SimpleCalendar testCal2 = new SimpleCalendar("Archeology Events", "Indiana Jones");
            CalendarEvent testCal2Ev1 = new CalendarEvent(testCal2, "Symposium", new Date(), "Lecture Hall",
                    null, new Date(), false);
            testCal2Ev1.addGuest("Brody");
            CalendarEvent testCal2Ev2 = new CalendarEvent(testCal2, "Punch Nazis", new Date(), "Outside",
                    null, new Date(), false);
            testCal2Ev1.addGuest("Nazis");

            repository.save(testCal2);
            eRepository.save(testCal2Ev1);
            eRepository.save(testCal2Ev2);

            log.info("Saved Customers, now loading them:");
            SimpleCalendar cal1 = repository.findById(1L).orElse(null);
            if(cal1 == null){ log.info("First retrieval was null");}
            else{ log.info(cal1.toString());}

            log.info("Saved an event, now loading:");
            CalendarEvent event1 = eRepository.findById(2L).orElse(null);
            if(event1 == null){ log.info("Event was null"); }
            else{ log.info(event1.toString()); }



        };
    }
}