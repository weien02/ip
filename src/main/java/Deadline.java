import exceptions.EmptyTaskNameException;

public class Deadline extends Task {

    private String by;

    public Deadline(String name, String by) throws EmptyTaskNameException {
        super(name);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String toSaveString() {
        return "D | " + (this.isDone ? "1 | " : "0 | ") + this.name + " | " + by;
    }
}