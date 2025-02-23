# DaveTheBrave User Guide

## Setting up in IntelliJ

Prerequisites: JDK 21.

1. Open IntelliJ (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
1. Open the project into IntelliJ as follows:
    1. Click `Open`.
    1. Select the project directory, and click `OK`.
    1. If there are any further prompts, accept the defaults.
1. Configure the project to use **JDK 21** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).<br>
   In the same dialog, set the **Project language level** field to the `SDK default` option.

The Main class is located in the `src/main/java/davethebrave/DaveTheBrave` file in the original repository [here](https://github.com/Jen999/ip).

## Functionalities
**✔️ Manage your own list**
1.  Add tasksFind tasks
2. Delete tasks
3. List all tasks
4. Find tasks

**✔️ Variety of task types**
1. To-Do tasks
2. Deadline tasks with end date (deadline)
3. Event tasks with start and end date

**✔️ Marking feature**
1. Mark tasks as done
2. Unmark tasks as undone

## Component Details
#### Task Manager:
- Handles all methods related to the list of tasks.
#### Task
- Handles all methods related to each individual task.
#### Parser
- Parses user commands into Task Manager operations to execute and generate output.
#### Storage
- Handles the storing and loading of the list of task from local file.
#### UI
- Handles the formatting of basic display messages to be called by Task Manager and Main classes.

## Starting out with DaveTheBrave
After you've had your introductions ("hi", "hey", "hello", "yo"), you can start adding tasks with:
```
// Add a To-Do task
todo Buy groceries

// Add a Deadline task
deadline Complete CS2103DE iP Week 5 Tasks /by 2025-02-14

// View your personal list
list

// Mark a task by its task number once you've completed it
mark 1

// Find your task
find CS2103DE

// Call it a day
bye
```
