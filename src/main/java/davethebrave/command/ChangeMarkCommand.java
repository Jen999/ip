package davethebrave.command;

import davethebrave.task.TaskManager;

/**
 * Base command for toggling task marker
 */
public class ChangeMarkCommand implements Command {
    private TaskManager taskManager;
    private String taskNumber;
    private boolean mark;

    public ChangeMarkCommand(TaskManager taskManager, String taskNumber, boolean mark) {
        this.taskManager = taskManager;
        this.taskNumber = taskNumber;
        this.mark = mark;
    }

    @Override
    public String execute() {
        return mark ? taskManager.markTask(taskNumber) : taskManager.unmarkTask(taskNumber);
    }
}

