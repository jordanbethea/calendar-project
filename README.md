# Simple Restful calendar

1. There should be a minimum of two models consisting of a calendar (name, user) and calendar events (calendar, title, event date and time, location, attendee list, reminder time, and whether the reminder has been sent).  
2. The app should expose a set of APIs to support the standard CRUD operations for calendar events.  
3. Data access should be accomplished using anything other than raw SQL queries to the database (JPA, Hibernate, JDO, etc)  
4. Implement APIs to allow a user to retrieve the list of events for a day, a week, or a month.
5. Implement a background service that sends out reminders based on the reminder time. Reminders can be printed to a log file or the console for this purpose.  
6. Implement user authentication using a token based mechanism of your choice so that users can only create/update/delete their own calendar events.  
7. Provide sample commands to retrieve a list of all events, a day/week/month's events, and for updating, deleting,  and creating new events, (curl, python script, java program, etc.).  
- If you implement #6, provide instructions on setting up a default user so that we are able to login to your application

