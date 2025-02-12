package davethebrave.command;

import davethebrave.task.TaskManager;

public class AddCommand implements Command {
    private TaskManager taskManager;
    private String type;
    private String description;
    private String details;

    public AddCommand(TaskManager taskManager, String type, String description) {
        this.taskManager = taskManager;
        this.type = type;
        this.description = description;
        this.details = null;
    }

    public AddCommand(TaskManager taskManager, String type, String description, String details) {
        this.taskManager = taskManager;
        this.type = type;
        this.description = description;
        this.details = details;
    }

    @Override
    public String execute() {
        return taskManager.addTask(type, description, details);
    }
}

