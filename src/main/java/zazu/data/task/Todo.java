package zazu.data.task;

/**
 * Represents a "To-Do" task in a task management system.
 * Inherits from the {@link Task} class and adds a specific representation
 * for a "To-Do" task.
 */
public class Todo extends Task {

    /**
     * Constructs a new Todo task with the specified description.
     * The task is initialized with the type "todo" and a not-done status.
     *
     * @param description the description of the "To-Do" task.
     */
    public Todo(String description) {
        super(description, "todo");
    }

    /**
     * Returns a string representation of the "To-Do" task.
     * The string includes the task type [T] followed by the details from the parent class.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
