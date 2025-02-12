package davethebrave.command;

import davethebrave.task.TaskManager;

public class FindCommand implements Command {
    private TaskManager taskManager;
    private String keyword;

    public FindCommand(TaskManager taskManager, String keyword) {
        this.taskManager = taskManager;
        this.keyword = keyword;
    }

    @Override
    public String execute() {
        return taskManager.findTask(keyword);
    }
}

