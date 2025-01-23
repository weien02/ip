public class Task {
    
    private String name;
    private boolean done;

    public Task(String name) {
        this.name = name;
        this.done = false;
    }

    public void markTaskDone() {
        this.done = true;
    }

    public void unmarkTaskDone() {
        this.done = false;
    }

    @Override
    public String toString() {
        String check = this.done ? "X" : " ";
        return "[" + check + "] " + this.name;
    }
}
