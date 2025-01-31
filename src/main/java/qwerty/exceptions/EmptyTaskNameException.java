package qwerty.exceptions;

public class EmptyTaskNameException extends BotException {

    @Override
    public String toString() {
        return super.toString() + " The description of a task cannot be empty!";
    }
}
