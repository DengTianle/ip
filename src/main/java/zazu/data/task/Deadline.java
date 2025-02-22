package zazu.data.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task in a task management system.
 * Inherits from the {@link Task} class and adds specific functionality for handling a deadline date.
 * The deadline has a date by which the task must be completed.
 */
public class Deadline extends Task {

    /** The deadline date by which the task must be completed. */
    protected LocalDate by;

    /**
     * Constructs a new Deadline task with the specified description and deadline date.
     * The task is initialized with the type "deadline" and a not-done status.
     *
     * @param description the description of the deadline task.
     * @param by the deadline date by which the task must be completed.
     */
    public Deadline(String description, LocalDate by) {
        super(description, "deadline");
        this.by = by;
    }

    /**
     * Return the deadline date converted to integer for sorting.
     *
     * @return deadline date in integer (yyyy-MM-dd) format
     */
    @Override
    public int valueForSort() {
        return Integer.parseInt(by.format(DateTimeFormatter.ofPattern("yyyyMMdd")));
    }

    /**
     * Returns a string representation of the deadline task.
     * Include status icon, description, and the
     * deadline date formatted as "d MMM yyyy".
     * The format is "[D] [statusIcon] description (by: deadlineDate)".
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + this.by.format(DateTimeFormatter.ofPattern("d MMM yyyy")) + ")";
    }
}
