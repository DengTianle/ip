package zazu.ui;

import zazu.data.TaskList;
import zazu.data.exception.InvalidIndexException;
import zazu.data.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * User interface class for interacting with the task list.
 * This class handles displaying messages to the user, reading input, and printing task information.
 */
public class Ui {
    /** The task list being managed by the UI */
    private TaskList list;

    /** Logo to display at the start of the program */
    private String logo = " ZZZZZ    AAAAA   ZZZZZ   U   U\n" +
            "    Z    A     A     Z    U   U\n" +
            "   Z     AAAAAAA    Z     U   U\n" +
            "  Z      A     A   Z      U   U\n" +
            " ZZZZZ   A     A  ZZZZZ   UUUUU";

    /** Scanner object for reading user input */
    private Scanner input;

    /**
     * Constructs a new {@code Ui} object with the specified task list.
     *
     * @param list The {@link TaskList} object to interact with.
     */
    public Ui(TaskList list) {
        this.list = list;
        this.input = new Scanner(System.in);
    }

    /**
     * Reads the next line of input from the user.
     *
     * @return The user input as a string.
     */
    public String readInput() {
        return input.nextLine();
    }

    /**
     * Prints a welcome message and the program logo.
     */
    public void printWelcome() {
        System.out.println("Hello! I'm \n" + logo);
        System.out.println("What can I do for you?\n");
    }

    /**
     * Prints the entire list of tasks.
     *
     * @throws InvalidIndexException If there is an invalid index when fetching tasks.
     */
    public void printList() throws InvalidIndexException {
        for (int i = 0; i < list.getSize(); i++) {
            System.out.println((i + 1) + "." + list.getTask(i).toString());
        }
        System.out.println();
    }

    /**
     * Prints the details of a specific task.
     *
     * @param task The {@link Task} to print.
     */
    public void printTask(Task task) {
        System.out.println("\t" + task.toString());
    }

    /**
     * Prints a message confirming that a task has been marked as done.
     *
     * @param task The {@link Task} that has been marked as done.
     */
    public void printMark(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        this.printTask(task);
        System.out.println();
    }

    /**
     * Prints a message confirming that a task has been deleted.
     *
     * @param task The {@link Task} that has been deleted.
     */
    public void printDelete(Task task) {
        System.out.println("Nice! I've deleted this task:");
        this.printTask(task);
        System.out.println();
    }

    /**
     * Prints a message confirming that a new task has been added.
     *
     * @param task The {@link Task} that has been added.
     */
    public void printAdd(Task task) {
        System.out.println("Got it. I've added this task:");
        this.printTask(task);
        System.out.println("Now you have " + list.getSize() + " tasks in the list.\n");
    }

    public void printFind(ArrayList<Task> tasks) {
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i).toString() + "\n");
        }
    }
}
