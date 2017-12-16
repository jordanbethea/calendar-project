package com.calendarproject.model;

public class SimpleCalendar {
    private String name;
    private String user;

    public void setName(String name){ this.name = name;}
    public String getName(){ return name;}

    public void setUser(String user){ this.user = user;}
    public String getUser(){ return user; }

    public SimpleCalendar(String name, String user){
        this.name = name;
        this.user = user;
    }
}
