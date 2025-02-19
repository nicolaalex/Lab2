// Event FIlters class
import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public class EventFilters {

    // Returns a map of event filters based on type or completion status
    public Map<String, Predicate<Event>> getEventFilters() {
        Map<String, Predicate<Event>> filters = new HashMap<String, Predicate<Event>>();
        final int NUMBER_OF_FILTERS = 3;  // Number of filters

        // Adding filters to the map
        filters.put("Filter Completed Events", getCompleteFilter());
        filters.put("Filter Deadlines", getDeadlineFilter());
        filters.put("Filter Meeting", getMeetingFilter());

        return filters;
    }

    // Filter for completed events
    public Predicate<Event> getCompleteFilter() {
        return (event) -> ((Completable)event).isComplete();
    }

    // Filter for Deadline events
    public Predicate<Event> getDeadlineFilter() {
        return (event) -> event instanceof Deadline;
    }

    // Filter for Meeting events
    public Predicate<Event> getMeetingFilter() {
        return (event) -> event instanceof Meeting;
    }
}
