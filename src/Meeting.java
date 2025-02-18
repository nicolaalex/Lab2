// Meeting Class
import java.time.Duration;
import java.time.LocalDateTime;

public class Meeting extends Event implements Completable {

    private LocalDateTime endDateTime;
    private String location;
    private boolean complete;

    // Constructor
    public Meeting(String name, LocalDateTime startDateTime, LocalDateTime endDateTime, String location) {
        super(name, startDateTime);
        this.endDateTime = endDateTime;
        this.location = location;
    }

    // Implement complete method from Completable interface
    @Override
    public void complete() {
        this.complete = true;
    }

    @Override
    public boolean isComplete() {
        return complete;
    }

    // Getter for endDateTime
    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    // Setter for endDateTime
    public void setEndDateTime(LocalDateTime end) {
        this.endDateTime = end;
    }

    // Getter for location
    public String getLocation() {
        return location;
    }

    // Setter for location
    public void setLocation(String location) {
        this.location = location;
    }

    // Duration between startDateTime and endDateTime
    public Duration getDuration() {
        return Duration.between(getDateTime(), endDateTime);
    }

    @Override
    public String getName() {
        return super.name;
    }
}
