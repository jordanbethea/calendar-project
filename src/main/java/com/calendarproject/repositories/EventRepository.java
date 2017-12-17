package com.calendarproject.repositories;

import com.calendarproject.model.CalendarEvent;
import org.springframework.data.repository.CrudRepository;

public interface EventRepository extends CrudRepository<CalendarEvent, Long> {

}
