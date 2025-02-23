package davethebrave.parser;

import davethebrave.command.Command;
import davethebrave.command.AddCommand;
import davethebrave.command.ChangeMarkCommand;
import davethebrave.command.DeleteCommand;
import davethebrave.command.FindCommand;
import davethebrave.command.InvalidCommand;
import davethebrave.command.ListCommand;
import davethebrave.command.ShowDeadlinesCommand;

import davethebrave.task.TaskManager;

/**
 * Parses user commands into TaskManager operations to execute and generate output
 */
public class Parser {
    public static Command parseCommand(String command, TaskManager taskManager) {
        /*
        Ensures that command is not null or empty
         */
        assert command != null && !command.trim().isEmpty() : "Command cannot be null or empty";
        /*
        Display list when requested
         */
        if (command.equalsIgnoreCase("list")) {
            return new ListCommand(taskManager);
        }
        else if (command.toLowerCase().startsWith("find ")) {
            String keyword = command.substring(5).trim();
            return keyword.isEmpty() ? new InvalidCommand("Invalid format. Use: find <keyword>") : new FindCommand(taskManager, keyword);
        }
        /*
        Add To-Do tasks
         */
        else if (command.toLowerCase().startsWith("todo ")) {
            String todoInfo = command.substring(5).trim();
            return new AddCommand(taskManager, "T", todoInfo);
        }
        /*
        Add Deadline tasks
         */
        else if (command.toLowerCase().startsWith("deadline ")) {
            String[] deadlineInfo = command.substring(9).split(" /by", 2);
            if (deadlineInfo.length == 2) {
                return new AddCommand(taskManager, "D", deadlineInfo[0].trim(), deadlineInfo[1].trim());
            } else {
                return new InvalidCommand("Invalid format. Use: deadline <task> /by yyyy-MM-dd");
            }
        }
        /*
        Add Event tasks
         */
        else if (command.toLowerCase().startsWith("event ")) {
            String[] eventInfo = command.substring(6).split(" /start | /end ", 3);
            if (eventInfo.length == 3) {
                return new AddCommand(taskManager, "E", eventInfo[0].trim(), "(start: " + eventInfo[1].trim() + ", end: " + eventInfo[2].trim() + ")");
            } else {
                return new InvalidCommand("Invalid format. Use: event <task> /start <start date/time> /end <end date/time>");
            }
        }
        /*
        Delete tasks
         */
        else if (command.toLowerCase().startsWith("delete ")) {
            String taskNumber = command.substring(7).trim();
            return new DeleteCommand(taskManager, taskNumber);
        }
        /*
        Mark tasks as done
         */
        else if (command.toLowerCase().startsWith("mark ")) {
            String taskNumber = command.substring(5).trim();
            return new ChangeMarkCommand(taskManager, taskNumber, true);
        }
        /*
        Unmark tasks as not done
         */
        else if (command.toLowerCase().startsWith("unmark ")) {
            String taskNumber = command.substring(7).trim();
            return new ChangeMarkCommand(taskManager, taskNumber, false);
        }
        /*
        Show deadline tasks in chronological order
         */
        else if (command.toLowerCase().startsWith("show deadline")) {
            return new ShowDeadlinesCommand(taskManager);
        }
        /*
        Handle Invalid Commands
         */
        else {
            return new InvalidCommand(
            "--Invalid Command--\n" +
            "Add to list\n" +
            "      'todo':  todo <task>\n" +
            "      'deadline':  deadline <task> /by <deadline date/time\n" +
            "      'event':  event <task> /start <start date/time> /end <end date/time>\n" +
            "View list\n" +
            "      'list':  list\n" +
            "View deadline tasks in chronological order\n" +
            "      'show deadlines':  show deadlines\n" +
            "Mark/Unmark tasks in list\n" +
            "      'mark':  mark <task>\n" +
            "      'unmark':  unmark <task>\n" +
            "Delete task from list\n" +
            "      'delete':  delete <task number>\n" +
            "Find task from list\n" +
            "      'find':  find <keyword>\n");
        }
    }
}
