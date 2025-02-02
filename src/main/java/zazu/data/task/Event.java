package zazu.data.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task in a task management system.
 * Inherits from the {@link Task} class and adds specific functionality for handling event dates.
 * The event has a start date ("from") and an end date ("to").
 */
public class Event extends Task {

    /** The start date of the event. */
    protected LocalDate from;

    /** The end date of the event. */
    protected LocalDate to;

    /**
     * Constructs a new Event task with the specified description, start date, and end date.
     * The task is initialized with the type "event" and a not-done status.
     *
     * @param description the description of the event.
     * @param from the start date of the event.
     * @param to the end date of the event.
     */
    public Event(String description, LocalDate from, LocalDate to) {
        super(description, "event");
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the start date of the event.
     */
    public LocalDate getFrom() {
        return this.from;
    }

    /**
     * Returns the end date of the event.
     */
    public LocalDate getTo() {
        return this.to;
    }

    /**
     * Returns a string representation of the event task.
     * Include status icon, description, and the event's
     * start and end dates formatted as "d MMM yyyy".
     * The format is "[E] [statusIcon] description (from: startDate to: endDate)".
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from.format(DateTimeFormatter.ofPattern("d MMM yyyy"))
                + " to: " + this.to.format(DateTimeFormatter.ofPattern("d MMM yyyy")) + ")";
    }
}
