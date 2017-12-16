package com.calendarproject.model;

import java.util.List;
import java.util.Date;

public class CalendarEvent {
    private SimpleCalendar calendar;
    private String title;
    private Date eventDate;
    private String location;
    private List<String> guests;
    private Date reminderDate;
    private Boolean isReminderSent;

    public void setCalendar(SimpleCalendar calendar){ this.calendar = calendar;}
    public SimpleCalendar getCalendar(){ return calendar; }

    public void setTitle(String title){ this.title = title;}
    public String getTitle(){ return title; }

    public void setEventDate(Date eventDate){ this.eventDate = eventDate; }
    public Date getEventDate(){ return eventDate; }

    public void setLocation(String location){ this.location = location; }
    public String getLocation(){ return location; }

    public void SetGuestList(List<String> guests){ this.guests = guests; }
    public List<String> getGuestList(){ return guests; }
    public void addGuest(String guest){ guests.add(guest);}
    public void removeGuest(String guest){guests.remove(guest);}

    public void setReminderDate(Date reminderDate){ this.reminderDate = reminderDate; }
    public Date getReminderDate(){ return reminderDate; }

    public void setIsReminderSent(Boolean isReminderSent){ this.isReminderSent = isReminderSent;}
    public Boolean getIsReminderSent(){ return isReminderSent; }

    public CalendarEvent(SimpleCalendar calendar, String title, Date eventDate,
                         String location, List<String> guests, Date reminderDate, Boolean isReminderSent){
        this.calendar = calendar;
        this.title = title;
        this.eventDate = eventDate;
        this.location = location;
        this.guests = guests;
        this.reminderDate = reminderDate;
        this.isReminderSent = isReminderSent;
    }
}
