package davethebrave;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import java.util.List;

import davethebrave.task.TaskManager;
import davethebrave.task.Task;
import davethebrave.storage.Storage;
import davethebrave.ui.Ui;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class TodoTest {
    private TaskManager taskManager;
    private List<Task> taskList;
    private Storage storage;
    private Ui ui;

    @BeforeEach
    void setUp() {
        taskList = new ArrayList<>();
        storage = new Storage("data/test.txt");
        ui = new Ui();
        taskManager = new TaskManager(taskList, storage, ui);
    }

    /*
    Test UI task added display
     */
    @Test
    public void displayTaskTest() {
        Task myFirstTask = new Task("T", "Complete Assignment", null);
        assertEquals("[T][ ] Complete Assignment", myFirstTask.toString());
    }

    /*
    Test adding task
     */
    @Test
    public void addTaskTest() {
        taskManager.addTask("T", "Complete Assignment", null);
        assertEquals(1, taskList.size(), "Task should be added into list.");
    }

    /*
    Test deleting task
     */
    @Test
    public void deleteTaskTest() {
        taskManager.deleteTask("1");
        assertTrue(taskList.isEmpty(), "Task should be deleted from list.");
    }

    /*
    Test mark/unmark task
     */
    @Test
    public void markUnmarkTaskTest() {
        taskManager.addTask("T", "Read a book", null);

        taskManager.markTask("1");
        assertTrue(taskList.get(0).toString().contains("[X]"), "Task should be marked as done.");

        taskManager.unmarkTask("1");
        assertFalse(taskList.get(0).toString().contains("[X]"), "Task should be unmarked and set to not done.");
    }

    /*
    Test invalid task number (mark/unmark)
     */
    @Test
    public void invalidMarkUnmarkTest() {
        int initialSize = taskList.size();
        taskManager.markTask("999");
        assertEquals(initialSize, taskList.size(), "Invalid task marking should not change task list.");

        taskManager.unmarkTask("999");
        assertEquals(initialSize, taskList.size(), "Invalid task marking should not change task list.");
    }
}
