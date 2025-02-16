/*
Handles methods related to the list of tasks
 */

package davethebrave.task;

import davethebrave.storage.Storage;
import davethebrave.ui.Ui;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.ArrayList;

public class TaskManager {
    private List<Task> tasks;
    private Storage storage;
    private Ui ui;

    String todoType = "T";
    String deadlineType = "D";
    String eventType = "E";

    public TaskManager(List<Task> tasks, Storage storage, Ui ui) {
        this.tasks = tasks;
        this.storage = storage;
        this.ui = ui;
        storage.checkFileExists();
        storage.loadTasksFromFile();
        System.out.println("Previous tasks loaded: ");
        listTasks();
    }
    public String addTask(String type, String task, String details) {
        if (type.equals(deadlineType) && details != null) {
            try {
                LocalDate.parse(details.trim(), Task.INPUT_FORMATTER);
                tasks.add(new Task(type, task, details));
                storage.saveTasksToFile(tasks);
                return "Added deadline: " + task + " (by: " + details + ")";
            } catch (DateTimeParseException e) {
                return "Invalid date format! Please use yyyy-MM-dd (e.g., 2019-10-15).";
            }
        }
        tasks.add(new Task(type, task, details));
        storage.saveTasksToFile(tasks);
        return ui.showTaskAdded(tasks);
    }

    public String deleteTask(String taskNumber) {
        if (isValidTaskNumber(taskNumber)) {
            Task removedTask = tasks.remove(Integer.parseInt(taskNumber) - 1);
            storage.saveTasksToFile(tasks);
            return ui.showTaskDeleted(removedTask, tasks);
        }
        return "Invalid Task Number.";
    }

    public String listTasks() {
        String output = "";
        if (tasks.isEmpty()) {
            return "No tasks to display.";
        } else {
            output += "Here are the tasks in your list:\n";
            for (int i = 0; i < tasks.size(); i++) {
                output += "      " + (i + 1) + "." + tasks.get(i) + "\n";
            }
            return output;
        }
    }

    public String markTask(String taskNumber) {
        String output = "";
        if (isValidTaskNumber(taskNumber)) {
            Task task = tasks.get(Integer.parseInt(taskNumber) - 1);
            if (!task.toString().contains("[X]")) {
                task.mark();
                storage.saveTasksToFile(tasks);
                output += "Nice! I've marked this task as done:\n";
            } else {
                output += "This task is already marked as done:\n";
            }
            return output + task;
        }
        return "Invalid Task Number.";
    }

    public String unmarkTask(String taskNumber) {
        String output = "";
        if (isValidTaskNumber(taskNumber)) {
            Task task = tasks.get(Integer.parseInt(taskNumber) - 1);
            if (task.toString().contains("[X]")) {
                task.unmark();
                storage.saveTasksToFile(tasks);
                output += "OK, I've marked this task as not done yet:\n";
            } else {
                output += "This task is already marked as not done:\n";
            }
            return output + task;
        }
        return "Invalid Task Number.";
    }

    public String findTask(String keyword) {
        List<Task> matchingTasks = new ArrayList<>();
        String output = "";

        for (int i = 0; i < tasks.size(); i++) {
            String taskDesc = tasks.get(i).getTaskDescription().toLowerCase();
            if (taskDesc.contains(keyword.toLowerCase())) {
                matchingTasks.add(tasks.get(i));
            }
        }

        if (matchingTasks.isEmpty()) {
            output += "No matching tasks found.";
        } else {
            output += "Matching tasks found:\n";
            for (int i = 0; i < matchingTasks.size(); i++) {
                output += "      " + (i + 1) + "." + matchingTasks.get(i) + "\n";
            }
        }
        return output;
    }

    private boolean isValidTaskNumber(String taskNumber) {
        try {
            int num = Integer.parseInt(taskNumber);
            /*
            Ensure task number is valid
             */
            assert num >= 1 && num <= tasks.size() : "Task number is out of bounds.";
            return num >= 1 && num <= tasks.size();
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
