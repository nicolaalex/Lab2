// Meeting Class


import java.time.Duration;
import java.time.LocalDateTime;

// Meeting Class extending Event and implementing Completable
public class Meeting extends Event implements Completable {

    // Variables
    private LocalDateTime endDateTime;
    private String location;
    private boolean complete;

    // Constructor
    public Meeting(String name, LocalDateTime startDateTime, LocalDateTime endDateTime, String location) {
        setName(name);  // Use setter to set name
        setDateTime(startDateTime);  // Use setter to set startDateTime
        this.endDateTime = endDateTime;
        this.location = location;
    }

    // Implementing the abstract getName method from Event class
    @Override
    public String getName() {
        return name;  // Use the inherited name property, which is set via the setter
    }

    // Sets complete boolean to true
    @Override
    public void complete() {
        this.complete = true;
    }

    // Returns complete status
    public boolean isComplete() {
        return complete;
    }

    // Setters and getters for endDate, location, and duration
    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    public String getLocation() {
        return location;
    }

    public Duration getDuration() {
        return Duration.between(getDateTime(), endDateTime);
    }

    public void setEndDateTime(LocalDateTime end) {
        this.endDateTime = end;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
