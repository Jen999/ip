package davethebrave.command;

import davethebrave.task.TaskManager;

/**
 * Base command for displaying deadline tasks chronologically
 */
public class ShowDeadlinesCommand implements Command {
    private TaskManager taskManager;

    public ShowDeadlinesCommand(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    @Override
    public String execute() {
        return taskManager.showDeadlines();
    }
}
