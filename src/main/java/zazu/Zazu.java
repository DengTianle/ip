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

public class Zazu {
    private TaskList list;
    private Ui ui;

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
    public static void main(String[] args) {
        new Zazu().run();
    }
}
