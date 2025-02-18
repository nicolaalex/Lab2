import java.time.LocalDateTime;

public abstract class Event implements Comparable<Event> {

    private String name;
    private LocalDateTime dateTime;

    // Constructor (optional)
    public Event() {}

    public Event(String name, LocalDateTime dateTime) {
        this.name = name;
        this.dateTime = dateTime;
    }

    // Abstract method for name (to be implemented by subclasses)
    public abstract String getName();

    // Getter for dateTime
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    // Setter for dateTime
    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    // Setter for name
    public void setName(String name) {
        this.name = name;
    }

    // Compare this Event with another Event by their dateTime
    @Override
    public int compareTo(Event e) {
        return this.dateTime.compareTo(e.getDateTime());
    }
}
