/*
Handles the main logic of running the program and scanning user input
 */

package davethebrave;

import davethebrave.command.Command;
import davethebrave.ui.Ui;
import davethebrave.task.TaskManager;
import davethebrave.storage.Storage;
import davethebrave.parser.Parser;

import java.util.Arrays;
import java.util.List;

public class DaveTheBrave {
    private Storage storage;
    private TaskManager taskManager;
    private Ui ui;
    private String commandType;

    public DaveTheBrave(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        taskManager = new TaskManager(storage.loadTasksFromFile(), storage, ui);
    }

    public static void main(String[] args) {
        System.out.println("Hello!");
    }

    public String getResponse(String input) {
        List<String> greetings = Arrays.asList("hello", "hi", "hey", "yo");
        List<String> goodbyes = Arrays.asList("bye", "goodbye");

        /*
        Exit when user types the command 'bye'
         */
        if (goodbyes.contains(input.toLowerCase())) {
            commandType = "GoodbyeCommand";
            return ui.showGoodbye();
        }

        /*
        Additional greetings
         */
        if (greetings.contains(input.toLowerCase())) {
            commandType = "GreetingCommand";
            return ui.respondHello();
        }

        /*
        Cheer
         */
        if (input.equalsIgnoreCase("cheer")) {
            commandType = "CheerCommand";
            return ui.showCheer();
        }
        try {
            Command c = Parser.parseCommand(input, taskManager);
            commandType = c.getClass().getSimpleName(); // Store the command type
            return c.execute();
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    public String getCommandType() {
        return commandType;
    }
}
