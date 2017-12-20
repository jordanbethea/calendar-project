# Simple Restful calendar

1. There should be a minimum of two models consisting of a calendar (name, user) and calendar events (calendar, title, event date and time, location, attendee list, reminder time, and whether the reminder has been sent).  
    * There is some kind of bug with the guest list right now, something to do with how Hibernate loads certain types of data on eager initialization:
    * https://stackoverflow.com/questions/6752424/elementcollection-java-persistence-hibernate-causes-loading-of-duplicate-inst
    * https://hibernate.atlassian.net/browse/HHH-6783
    * The short version is that if you add multiple guests, the system loads the events with an outer join, and so loads as many events as there are guests. According to hibernate, this is not a bug.
    * As such, right now it only supports one guest per event, until I can figure out how to properly run lazy initialization.
    * There is also an existing bug, when editing an event, it will not display the existing guest, but it will be overridden by whatever the new input guest is.
2. The app should expose a set of APIs to support the standard CRUD operations for calendar events.  
    * All CRUD operations are currently supported through web UI. I used web instead of raw JSON just because I'm more familiar with it, and with testing through pages.
    * The same functions could be converted to a straight REST controller just by changing from @Controller to @RestController, and changing the function outputs to return the raw data objects for JSON, instead of the web templates.
3. Data access should be accomplished using anything other than raw SQL queries to the database (JPA, Hibernate, JDO, etc)  
    * Data is currently saved through JPA.
4. ~~Implement APIs to allow a user to retrieve the list of events for a day, a week, or a month.~~
5. ~~Implement a background service that sends out reminders based on the reminder time. Reminders can be printed to a log file or the console for this purpose.~~
6. ~~Implement user authentication using a token based mechanism of your choice so that users can only create/update/delete their own calendar events~~
7. ~~Provide sample commands to retrieve a list of all events, a day/week/month's events, and for updating, deleting,  and creating new events, (curl, python script, java program, etc.).~~
- ~~If you implement #6, provide instructions on setting up a default user so that we are able to login to your application~~

