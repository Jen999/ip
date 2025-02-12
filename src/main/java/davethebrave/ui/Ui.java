/*
Handles the formatting of basic display messages to be called by TaskManager and Main classes
 */

package davethebrave.ui;

import davethebrave.task.Task;

import java.util.List;

public class Ui {
    public String showWelcome() {
        return "Boooo! I'm davethebrave.DaveTheBrave";
    }

    public String respondHello() {
        return "Hey, what's up?";
    }

    public String showGoodbye() {
        return "Bye! Hope I didn't scare you away!";
    }

    public String showCheer() {
        return "Keep going - even the best programmers started out writing 'Hello World'!";
    }

    public String showTaskAdded(List<Task> tasks) {
        return "Got it. I've added this task:\n"
                + tasks.getLast() + "\n"
                + "Now you have " + tasks.size() + " tasks in the list.";
    }

    public String showTaskDeleted(Task removedTask, List<Task> tasks) {
        return "Noted. I've removed this task:\n"
                + removedTask + "\n"
                + "Now you have " + tasks.size() + " tasks in the list.";
    }
}
