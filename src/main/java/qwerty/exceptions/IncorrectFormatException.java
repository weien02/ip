package qwerty.exceptions;

public class IncorrectFormatException extends BotException {
    private String commandType;

    public IncorrectFormatException(String commandType) {
        super();
        this.commandType = commandType;
    }

    @Override
    public String toString() {
        return super.toString() + " Please make sure your " + commandType + " description is formatted correctly!";
    }
}
