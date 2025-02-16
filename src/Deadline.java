public class Deadline extends Event implements Completable{

    //holds a Boolean representing whether the task this deadline tracks is complete
    private boolean complete;

    // Constuctor
    public Deadline(String name, LocalDateTime endDateTime) {
        setName(name);
        setDateTime(endDateTime);
    }

    //sets the complete boolean to true
    public void complete() {
        this.complete = true;
    }

    // returns complete boolean
    public boolean isComplete() {
        return this.complete;
    }

}
