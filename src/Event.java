// Abstract Event Class implementing Comparable<Event>

import java.time.LocalDateTime;

public abstract class Event implements Comparable<Event> {
    protected String name;
    private LocalDateTime dateTime;

    // Constructor
    public Event() {}

    // Constructor with parameters for name and dateTime
    public Event(String name, LocalDateTime dateTime) {
        this.name = name;
        this.dateTime = dateTime;
    }

    // Abstract method to get the name of the event
    public abstract String getName();

    // Getter for the dateTime
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    // Setter for the dateTime
    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    // Setter for the name
    public void setName(String name) {
        this.name = name;
    }

    // Compares the events based on dateTime
    @Override
    public int compareTo(Event e) {
        return this.dateTime.compareTo(e.dateTime);
    }
}
