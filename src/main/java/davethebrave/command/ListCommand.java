package davethebrave.command;

import davethebrave.task.TaskManager;

/**
 * Base command for displaying list information to user
 */
public class ListCommand implements Command {
    private TaskManager taskManager;

    public ListCommand(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    @Override
    public String execute() {
        return taskManager.listTasks();
    }
}

