import exceptions.EmptyTaskNameException;

public class Task {
    
    protected String name;
    protected boolean isDone;

    public Task(String name) throws EmptyTaskNameException {
        if (name.length() == 0) {
            throw new EmptyTaskNameException();
        }
        this.name = name;
        this.isDone = false;
    }

    public void markTaskDone() {
        this.isDone = true;
    }

    public void unmarkTaskDone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        String check = this.isDone ? "X" : " ";
        return "[" + check + "] " + this.name;
    }

    public String toSaveString() {
        return "";
    };

}
