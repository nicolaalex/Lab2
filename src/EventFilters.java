/*Event Filters provides a set of filters*/
import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

//EventFilter Class
public class EventFilters {

    //Maps Filters to the events
    public Map<String, Predicate<Event>> getEventFilters() {
        Map<String, Predicate<Event>> filters = new HashMap<String, Predicate<Event>>();
        final int NUMBER_OF_FILTERS = 3;
        filters.put("Filter Completed Events", getCompleteFilter());
        filters.put("Filter Deadlines", getDeadlineFilter());
        filters.put("Filter Meeting", getMeetingFilter());
        return filters;
    }

    //Completed events
    public Predicate<Event> getCompleteFilter() {
        return (event) -> {return ((Completable)event).isComplete();};
    }
    //Deadline events
    public Predicate<Event> getDeadlineFilter() {
        return (event) -> {return event instanceof Deadline;};
    }
    //Meeting Events
    public Predicate<Event> getMeetingFilter() {
        return (event) -> {return event instanceof Meeting;};
    }
}
