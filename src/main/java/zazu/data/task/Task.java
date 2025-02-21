package zazu.data.task;

/**
 * Represents a generic task in a task management system.
 * This class provides the basic structure for tasks, including their description,
 * type, and completion status. It also provides methods to manipulate and retrieve
 * information about the task.
 */
public class Task {

    /** The description of the task. */
    protected String description;

    /** The type of the task (e.g., "todo", "event", "deadline"). */
    protected String type;

    /** The completion status of the task. */
    protected boolean isDone;

    /**
     * Constructs a new Task with the specified description and type.
     * The task is initialized as not done.
     *
     * @param description the description of the task.
     * @param type the type of the task (e.g., "todo", "deadline").
     */
    public Task(String description, String type) {
        this.description = description;
        this.type = type;
        this.isDone = false;
    }

    /**
     * Returns the status icon of the task. If the task is done, it returns "X",
     * otherwise it returns a space.
     *
     * @return the status icon of the task ("X" if done, space otherwise).
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns the description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns a string representation of the task.
     * Including its status icon and description.
     * The format is "[statusIcon] description".
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
