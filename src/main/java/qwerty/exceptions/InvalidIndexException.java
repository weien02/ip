package qwerty.exceptions;

public class InvalidIndexException extends BotException {

    @Override
    public String toString() {
        return super.toString() + " Please make sure you enter a valid index!";
    }
}
