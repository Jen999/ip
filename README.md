# DaveTheBrave
> Based on the Duke Project 
> "Embrace technological advancement, don't fear it." - Dave, the brave 🦸🏻‍♂️

Your personal companion to ensure you never have to worry about forgetting a task ever again.

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

## Feature Updates
- [x] Basic management of tasks
- [x] Add "find/search" feature
- [ ] Enhancing UI
- [ ] Add "categorizing/filtering" feature

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
