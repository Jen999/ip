package davethebrave.command;

import davethebrave.task.TaskManager;

/**
 * Base command for deleting tasks
 */
public class DeleteCommand implements Command {
    private TaskManager taskManager;
    private String taskNumber;

    public DeleteCommand(TaskManager taskManager, String taskNumber) {
        this.taskManager = taskManager;
        this.taskNumber = taskNumber;
    }

    @Override
    public String execute() {
        return taskManager.deleteTask(taskNumber);
    }
}

