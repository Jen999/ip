/*
Parses user commands into TaskManager operations to execute and generate output
 */

package davethebrave.parser;

import davethebrave.task.TaskManager;

public class Parser {
    public static String parseCommand(String command, TaskManager taskManager) {
        /*
        Display list when requested
         */
        if (command.equalsIgnoreCase("list")) {
            return taskManager.listTasks();
        }
        else if (command.toLowerCase().startsWith("find ")) {
            String keyword = command.substring(5).trim();
            if (!keyword.isEmpty()) {
                return taskManager.findTask(keyword);
            } else {
                return "Invalid format. Use: find <keyword>";
            }
        }
        // Add To-Do tasks
        else if (command.toLowerCase().startsWith("todo ")) {
            String todoInfo = command.substring(5).trim();
            if (!todoInfo.isEmpty()) {
                return taskManager.addTask("T", todoInfo, null);
            } else {
                return "Invalid format. Use: todo <task>";
            }
        }
        /*
        Add Deadline tasks
         */
        else if (command.toLowerCase().startsWith("deadline ")) {
            String[] deadlineInfo = command.substring(9).split(" /by", 2);
            if (deadlineInfo.length == 2) {
                String task = deadlineInfo[0].trim();
                String deadline = deadlineInfo[1].trim();
                return taskManager.addTask("D", task, deadline);
            } else {
                return "Invalid format. Use: deadline <task> /by yyyy-MM-dd";
            }
        }
        /*
        Add Event tasks
         */
        else if (command.toLowerCase().startsWith("event ")) {
            String[] eventInfo = command.substring(6).split(" /start | /end ", 3);
            if (eventInfo.length == 3) {
                String task = eventInfo[0].trim();
                String start = eventInfo[1].trim();
                String end = eventInfo[2].trim();
                return taskManager.addTask("E", task, "(start: " + start + ", end: " + end + ")");
            } else {
                return "Invalid format. Use: event <task> /start <start date/time> /end <end date/time>";
            }
        }
        /*
        Delete tasks
         */
        else if (command.toLowerCase().startsWith("delete ")) {
            if (command.substring(7).trim().isEmpty()) {
                return "Task Number cannot be empty.";
            }
            int taskNumber = Integer.parseInt(command.substring(7).trim());
            return taskManager.deleteTask(taskNumber);
        }
        /*
        Mark tasks as done
         */
        else if (command.toLowerCase().startsWith("mark ")) {
            if (command.substring(5).trim().isEmpty()) {
                return "Task Number cannot be empty.";
            }
            int taskNumber = Integer.parseInt(command.substring(5).trim());
            return taskManager.markTask(taskNumber);
        }
        /*
        Unmark tasks as not done
         */
        else if (command.toLowerCase().startsWith("unmark ")) {
            if (command.substring(7).trim().isEmpty()) {
                return "Task Number cannot be empty.";
            }
            int taskNumber = Integer.parseInt(command.substring(7).trim());
            return taskManager.unmarkTask(taskNumber);
        }
        /*
        Handle Invalid Commands
         */
        else {
            return
            "--Invalid Command--\n" +
            "Add to list\n" +
            "      'todo':         todo <task>\n" +
            "      'deadline':     deadline <task> /by <deadline date/time\n" +
            "      'event':        event <task> /start <start date/time> /end <end date/time>\n" +
            "View list\n" +
            "      'list':         list\n" +
            "Mark/Unmark tasks in list\n" +
            "      'mark':         mark <task>\n" +
            "      'unmark':       unmark <task>\n" +
            "Delete task from list\n" +
            "      'delete':       delete <task number>\n" +
            "Find task from list\n" +
            "      'find':       find <keyword>\n";
        }
    }
}
