import java.time.LocalDateTime;

public class Deadline extends Event {

    // Constructor for Deadline
    public Deadline(String name, LocalDateTime dateTime) {
        super(name, dateTime);  // Call the superclass constructor
    }

    // Implementing the abstract method to return the name
    @Override
    public String getName() {
        return super.getName();  // Use the inherited getter from Event
    }
}
