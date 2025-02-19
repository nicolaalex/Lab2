// Deadline Class extending Event and implementing Completable

import java.time.LocalDateTime;

public class Deadline extends Event implements Completable {
    // Boolean variable to track whether the deadline is complete or not
    private boolean complete;

    // Constructor to initialize the Deadline with a name and end dateTime
    public Deadline(String name, LocalDateTime endDateTime) {
        setName(name);  // Use setter to assign the event's name
        setDateTime(endDateTime);  // Use setter to assign the event's dateTime
    }

    // Returns the name of the Deadline event (inherited from Event class)
    @Override
    public String getName() {
        return name;  // retrieves the name via the setter in the Event class
    }

    // Marks the Deadline as complete by setting the 'complete' status to true
    public void complete() {
        this.complete = true;
    }

    // Returns whether the Deadline is marked as complete
    public boolean isComplete() {
        return this.complete;  // Returns the 'complete' status of the Deadline
    }
}
