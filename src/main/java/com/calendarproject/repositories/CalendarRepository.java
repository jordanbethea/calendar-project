package com.calendarproject.repositories;

import com.calendarproject.model.SimpleCalendar;
import org.springframework.data.repository.CrudRepository;

public interface CalendarRepository extends CrudRepository<SimpleCalendar, Long> {

}
