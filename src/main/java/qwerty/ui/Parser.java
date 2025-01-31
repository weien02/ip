package ui;
import commands.BotCommand;
import commands.ByeCommand;
import commands.CommandsEnum;
import commands.DeadlineCommand;
import commands.DeleteCommand;
import commands.EventCommand;
import commands.ListCommand;
import commands.MarkCommand;
import commands.TodoCommand;
import commands.UnmarkCommand;
import exceptions.BotException;
import exceptions.InvalidIndexException;
import exceptions.UnknownCommandException;

public class Parser {

    public static int validateIndex(String desc) throws BotException{
        int index;
        try {
            index = Integer.parseInt(desc) - 1;
        } catch (NumberFormatException n){
            throw new InvalidIndexException();
        }
        return index;
    }

    public static BotCommand parse(String fullCommand) throws BotException {
        try {
            int spaceIndex = fullCommand.indexOf(' ');
    
            String command, desc;
            if (spaceIndex != -1) {
                command = fullCommand.substring(0, spaceIndex);
                desc = fullCommand.substring(spaceIndex + 1);
            } else {
                command = fullCommand;
                desc = "";
            }
    
            CommandsEnum.contains(command);
    
            switch (command) {
                case "bye":
                    return new ByeCommand();
                case "list":
                    return new ListCommand();
                case "mark":
                    return new MarkCommand(validateIndex(desc));
                case "unmark":
                    return new UnmarkCommand(validateIndex(desc));
                case "delete":
                    return new DeleteCommand(validateIndex(desc));
                case "todo":
                    return new TodoCommand(desc);
                case "deadline":
                    return new DeadlineCommand(desc);
                case "event":
                    return new EventCommand(desc);
                default:
                    throw new UnknownCommandException();
            }
        } catch (BotException e) {
            throw e;
        } catch (Exception e) {
            throw new UnknownCommandException();
        }

    }
}
