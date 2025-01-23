import exceptions.EmptyTaskNameException;

public class Event extends Task {
    
    private String from;
    private String to;

    public Event(String name, String from, String to) throws EmptyTaskNameException {
        super(name);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
