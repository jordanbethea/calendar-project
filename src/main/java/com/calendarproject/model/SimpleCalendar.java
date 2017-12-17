package com.calendarproject.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;
import javax.persistence.FetchType;

@Entity
public class SimpleCalendar {

    @OneToMany(fetch = FetchType.EAGER, mappedBy="calendar")
    private List<CalendarEvent> events;

    public void setEvents(List<CalendarEvent> events){ this.events = events;}
    public List<CalendarEvent> getEvents(){ return events; }

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;

    private String name;
    private String user;

    public void setId(long id){ this.id = id;}
    public long getId(){ return id; }

    public void setName(String name){ this.name = name;}
    public String getName(){ return name;}

    public void setUser(String user){ this.user = user;}
    public String getUser(){ return user; }

    protected SimpleCalendar(){}

    public SimpleCalendar(String name, String user){
        this.name = name;
        this.user = user;
    }

    @Override
    public String toString(){
        return "Simple Calendar(Name: "+name+", User: "+user+")";
    }
}
