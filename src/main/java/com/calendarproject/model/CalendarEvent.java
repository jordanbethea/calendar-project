package com.calendarproject.model;

import java.util.Set;
import java.util.HashSet;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.ElementCollection;
import javax.persistence.FetchType;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class CalendarEvent {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    private SimpleCalendar calendar;

    //lazy initialization is more efficient, but I'm not sure the proper way to implement it as yet, that would be a todo
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> guests;

    private String title;
    private Date eventDate;
    private String location;
    private Date reminderDate;
    private Boolean isReminderSent;

    public void setId(long id){ this.id = id;}
    public long getId(){ return id; }

    public void setCalendar(SimpleCalendar calendar){ this.calendar = calendar;}
    public SimpleCalendar getCalendar(){ return calendar; }

    public void setTitle(String title){ this.title = title;}
    public String getTitle(){ return title; }

    public void setEventDate(Date eventDate){ this.eventDate = eventDate; }
    public Date getEventDate(){ return eventDate; }

    public void setLocation(String location){ this.location = location; }
    public String getLocation(){ return location; }

    public void setGuestList(Set<String> guests){ this.guests = guests; }
    public Set<String> getGuestList(){ return guests; }
    public void addGuest(String guest){ guests.add(guest);}
    public void removeGuest(String guest){guests.remove(guest);}

    public void setReminderDate(Date reminderDate){ this.reminderDate = reminderDate; }
    public Date getReminderDate(){ return reminderDate; }

    public void setIsReminderSent(Boolean isReminderSent){ this.isReminderSent = isReminderSent;}
    public Boolean getIsReminderSent(){ return isReminderSent; }

    protected CalendarEvent(){}

    public CalendarEvent(SimpleCalendar calendar, String title, Date eventDate,
                         String location, Set<String> guests, Date reminderDate, Boolean isReminderSent){
        this.calendar = calendar;
        this.title = title;
        this.eventDate = eventDate;
        this.location = location;
        if(guests != null) {
            this.guests = guests;
        }else { this.guests = new HashSet<String>(); }
        this.reminderDate = reminderDate;
        this.isReminderSent = isReminderSent;
    }

    public String toString(){
        String format = "CalendarEvent(Calendar name: %1$s, Event Title: %2$s, Date %3$s, Location: %4$s, Reminder: %5$s,"+
                " Is Reminder Sent: %6$b, Guests: %7$s";
        String output = String.format(format, calendar.getName(),title,eventDate,location,reminderDate,
                isReminderSent,guests);
        return output;
    }
}
