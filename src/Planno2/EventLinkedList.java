
package Planno2;

    // Question :
    // Write the question here
    // eg: if possible

import java.util.LinkedList;

public class EventLinkedList {

    private LinkedList<Event> events;

    public EventLinkedList() {
        events = new LinkedList<>();
    }

    public LinkedList<Event> getEvents() {
        return events;
    }

    public void addEvent(Event event) {
        events.add(event);
    }

    // Add other methods if necessary
    //This will be change from time to time; Depends on farid, kori and syakeer :DD
}

