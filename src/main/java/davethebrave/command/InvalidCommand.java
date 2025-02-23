package davethebrave.command;

/**
 * Base command for handling invalid commands
 */
public class InvalidCommand implements Command {
    private String message;

    public InvalidCommand(String message) {
        this.message = message;
    }

    @Override
    public String execute() {
        return message;
    }
}

