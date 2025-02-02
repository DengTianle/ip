package zazu;

import zazu.data.TaskList;
import zazu.data.exception.EmptyDescriptionException;
import zazu.data.exception.IncompleteCommandException;
import zazu.data.exception.InvalidIndexException;
import zazu.data.exception.UnknownCommandException;
import zazu.data.task.Task;
import zazu.data.task.Todo;
import zazu.data.task.Deadline;
import zazu.data.task.Event;
import zazu.parser.Parser;
import zazu.storage.Storage;
import zazu.ui.Ui;
import java.time.format.DateTimeParseException;
import java.time.LocalDate;

/**
 * Main class for running the Zazu chatbot application.
 * This class manages the main application flow, including task management,
 * user interactions, and saving/loading tasks.
 */
public class Zazu {

    /** The task list managed by the application */
    private TaskList list;

    /** The user interface that interacts with the user */
    private Ui ui;

    /**
     * Runs the Zazu chatbot application.
     * This method loads the task list, continuously prompts for user input,
     * and processes commands until the user exits.
     */
    public void run() {
        list = new TaskList(Storage.loadTasks());
        this.ui = new Ui(list);
        this.ui.printWelcome();

        int index;
        String description;
        String[] result;
        Task task;

        boolean isRunning = true;
        while (isRunning) {
            try {
                String str = ui.readInput();

                switch (Parser.identifyCommand(str)) {
                    case Parser.BYE:
                        isRunning = false;
                        break;
                    case Parser.LIST:
                        ui.printList();
                        break;
                    case Parser.MARK:
                        index = Parser.parseIndex(str);
                        task = list.getTask(index);
                        task.markAsDone();
                        ui.printMark(task);
                        break;
                    case Parser.DELETE:
                        index = Parser.parseIndex(str);
                        task = list.deleteTask(index);
                        ui.printDelete(task);
                        break;
                    case Parser.TODO:
                        description = Parser.parseDescription(str);
                        task = new Todo(description);
                        list.addTask(task);
                        ui.printAdd(task);
                        break;
                    case Parser.DEADLINE:
                        result = Parser.parseDeadline(str);
                        String byStr = result[1];
                        description = result[0];
                        task = new Deadline(description, LocalDate.parse(byStr));
                        list.addTask(task);
                        ui.printAdd(task);
                        break;
                    case Parser.EVENT:
                        result = Parser.parseEvent(str);
                        String fromStr = result[1];
                        String toStr = result[2];
                        description = result[0];
                        task = new Event(description, LocalDate.parse(fromStr), LocalDate.parse(toStr));
                        list.addTask(task);
                        ui.printAdd(task);
                        break;
                }
            } catch (InvalidIndexException | EmptyDescriptionException | IncompleteCommandException |
                     UnknownCommandException e) {
                System.err.println(e.getMessage() + "\n");
            } catch (DateTimeParseException e) {
                System.err.println("Error: " + "please enter time in the correct format. " + "\n");
            } catch (NumberFormatException e) {
                System.err.println(new InvalidIndexException().getMessage() + "\n");
            } catch (Exception e) {
                System.err.println("Unknown Error: please check your input and try again. \n");
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
        Storage.saveTasks(list.getList());
    }

    /**
     * Main method to run the Zazu application.
     * Initializes and starts the Zazu application.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        new Zazu().run();
    }
}
