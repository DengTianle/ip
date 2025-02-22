package zazu.parser;

import zazu.data.TaskList;
import zazu.data.exception.InvalidIndexException;
import zazu.data.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * User interface class for interacting with the task list.
 * This class handles displaying messages to the user, reading input, and printing task information.
 */
public class OutputFormatter {
    /** The task list being managed by the UI */
    private TaskList list;

    /** Logo to display at the start of the program */
    private String logo = " ZZZZZ    AAAAA   ZZZZZ   U   U\n" +
            "    Z    A     A     Z    U   U\n" +
            "   Z     AAAAAAA    Z     U   U\n" +
            "  Z      A     A   Z      U   U\n" +
            " ZZZZZ   A     A  ZZZZZ   UUUUU";

    /**
     * Constructs a new {@code Ui} object with the specified task list.
     *
     * @param list The {@link TaskList} object to interact with.
     */
    public OutputFormatter(TaskList list) {
        assert list != null;
        this.list = list;
    }

    /**
     * Prints the entire list of tasks.
     *
     * @throws InvalidIndexException If there is an invalid index when fetching tasks.
     */
    public String printList() throws InvalidIndexException {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.getSize(); i++) {
            sb.append((i + 1) + "." + list.getTask(i).toString());
            sb.append("\n");
        }
        return sb.toString();
    }

    /**
     * Prints the details of a specific task.
     *
     * @param task The {@link Task} to print.
     */
    public String printTask(Task task) {
        return "\t" + task.toString();
    }

    /**
     * Prints a message confirming that a task has been marked as done.
     *
     * @param task The {@link Task} that has been marked as done.
     */
    public String printMark(Task task) {
        StringBuilder sb = new StringBuilder();
        sb.append("Nice! I've marked this task as done:");
        sb.append("\n");
        sb.append(this.printTask(task));
        sb.append("\n");
        return sb.toString();
    }

    /**
     * Prints a message confirming that a task has been deleted.
     *
     * @param task The {@link Task} that has been deleted.
     */
    public String printDelete(Task task) {
        StringBuilder sb = new StringBuilder();
        sb.append("Nice! I've deleted this task:");
        sb.append("\n");
        sb.append(this.printTask(task));
        sb.append("\n");
        return sb.toString();
    }

    /**
     * Prints a message confirming that a new task has been added.
     *
     * @param task The {@link Task} that has been added.
     */
    public String printAdd(Task task) {
        StringBuilder sb = new StringBuilder();
        sb.append("Got it. I've added this task:");
        sb.append("\n");
        sb.append(this.printTask(task));
        sb.append("\n");
        sb.append("Now you have " + list.getSize() + " tasks in the list.\n");
        return sb.toString();
    }

    public String printFind(ArrayList<Task> tasks) {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the matching tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            sb.append((i + 1) + "." + tasks.get(i).toString() + "\n");
        }
        return sb.toString();
    }

    public String printSort() throws InvalidIndexException {
        return "I have sorted the list in chronological order!" + "\n" + this.printList();
    }
}
