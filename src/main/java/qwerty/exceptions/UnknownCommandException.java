package qwerty.exceptions;

public class UnknownCommandException extends BotException {
    
    @Override
    public String toString() {
        return super.toString() + " I don't understand the words that are coming out of your mouth!";
    }
}
