package task;
import exceptions.EmptyTaskNameException;

public class ToDo extends Task{

    public ToDo(String name) throws EmptyTaskNameException {
        super(name);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toSaveString() {
        return "T | " + (this.isDone ? "1 | " : "0 | ") + this.name;
    }
}
