package zazu.data;

import zazu.data.exception.InvalidIndexException;
import zazu.data.task.Task;

import java.util.ArrayList;

/**
 * Represents a list of tasks and provides operations to manipulate the list.
 * This class allows adding, deleting, retrieving, and checking the size of tasks.
 */
public class TaskList {
    private ArrayList<Task> list;

    /**
     * Constructs a new {@code TaskList} with the specified list of tasks.
     * If the provided list is {@code null}, an empty list is initialized.
     *
     * @param tasks The list of tasks to initialize the {@code TaskList} with.
     */
    public TaskList(ArrayList<Task> tasks) {
        if (tasks == null) {
            this.list = new ArrayList<>();
        } else {
            this.list = tasks;
        }
    }

    /**
     * Adds a task to the {@code TaskList}.
     *
     * @param task The task to be added to the list.
     */
    public void addTask(Task task) {
        this.list.add(task);
    }

    /**
     * Deletes a task from the {@code TaskList} at the specified index.
     * Throws an {@link InvalidIndexException} if the index is out of bounds.
     *
     * @param index The index of the task to be deleted.
     * @return The task that was deleted.
     * @throws InvalidIndexException If the provided index is invalid.
     */
    public Task deleteTask(int index) throws InvalidIndexException {
        Task task = this.getTask(index);
        list.remove(index);
        return task;
    }

    /**
     * Returns the size of the {@code TaskList}.
     *
     * @return The number of tasks in the list.
     */
    public int getSize() {
        return this.list.size();
    }

    /**
     * Retrieves the task at the specified index.
     * Throws an {@link InvalidIndexException} if the index is out of bounds.
     *
     * @param index The index of the task to retrieve.
     * @return The task at the specified index.
     * @throws InvalidIndexException If the provided index is invalid.
     */
    public Task getTask(int index) throws InvalidIndexException {
        if (index < 0 || index >= list.size()) {
            throw new InvalidIndexException();
        }
        return this.list.get(index);
    }

    /**
     * Returns the list of tasks.
     * This is necessary for saving the list to file.
     */
    public ArrayList<Task> getList() {
        return this.list;
    }
}
