// this is the Completable interface

public interface Completable {

    // Marks the event as complete
    // This method should be called when event is finished/completed
    void complete();

    // Returns true if the event is marked as complete, false otherwise
    // This method checks if the event has been completed.
    boolean isComplete();
}