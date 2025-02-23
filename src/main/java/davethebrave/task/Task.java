package davethebrave.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Handles methods related to each individual task
 */
public class Task {
    private String type; // "T" = To-Do, "D" = Deadline, "E" = Event
    private String description;
    private boolean status; // false = not done, true = done
    private String details; // Info for Event tasks
    private LocalDate deadline; // Info for Deadline tasks

    String todoType = "T";
    String deadlineType = "D";
    String eventType = "E";

    public static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy");

    public Task(String type, String task, String details) {
        this.type = type;
        this.description = task;
        this.status = false;
        this.details = details;

        /*
        Handling specific task types (i.e., deadline tasks)
         */
        if (type.equals(deadlineType) && details != null) {
            try {
                this.deadline = LocalDate.parse(details.trim(), INPUT_FORMATTER);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format! Please use yyyy-MM-dd (e.g., 2019-10-15).");
            }
        }
    }

    public String getType() {
        return type;
    }

    public LocalDate getDate() {
        return deadline != null ? deadline : LocalDate.MAX;
    }

    public String getTaskDescription() {
        return this.description;
    }

    public void mark() {
        this.status = true;
    }

    public void unmark() {
        this.status = false;
    }

    @Override
    public String toString() {
        String taskStatus = status ? "[X]" : "[ ]";
        if (type.equals(deadlineType)) {
            String taskDetails = details != null ? " (by: " + deadline.format(OUTPUT_FORMATTER) + ")" : "";
            return "[" + type + "]" + taskStatus + " " + description + taskDetails;
        } else {
            String taskDetails = details != null ? " " + details : "";
            return "[" + type + "]" + taskStatus + " " + description + (taskDetails);
        }
    }

    /*
    Formatting task to write to data file
     */
    public String toFileFormat() {
        return type + " | " + (status ? "[X]" : "[ ]") + " | " + description + (details != null ? " | " + details : "");
    }

    /*
    Loading tasks from data file
     */
    public static Task fromFileFormat(String line) {
        String[] info = line.split("\\s*\\|\\s*");
        /*
        Ignore invalid lines
         */
        if (info.length < 3) {
            return null;
        }
        String type = info[0];
        boolean status = info[1].equals("[X]");
        String task = info[2];
        String details = info.length > 3 ? info[3] : null;
        Task loadedTask = new Task(type, task, details);
        if (status) {
            loadedTask.mark();
        }
        return loadedTask;
    }
}
