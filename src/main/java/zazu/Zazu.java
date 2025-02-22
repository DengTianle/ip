package zazu;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
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
import zazu.ui.MainWindow;
import zazu.parser.OutputFormatter;

import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Main class for running the Zazu chatbot application.
 * This class manages the main application flow, including task management,
 * user interactions, and saving/loading tasks.
 */
public class Zazu extends Application {

    /** The task list managed by the application */
    private TaskList list;

    /** The class that formats the final output texts */
    private OutputFormatter out;

    /**
     * Runs the Zazu chatbot application.
     * This method loads the task list, continuously prompts for user input,
     * and processes commands until the user exits.
     */

    private void handleExit() {
        Storage.saveTasks(this.list.getList());
        Platform.exit();
    }

    public String getResponse(String str) {
        int index;
        String description;
        String[] result;
        Task task;

        try {
            switch (Parser.identifyCommand(str)) {
            case Parser.BYE:
                this.handleExit();
                break;
            case Parser.LIST:
                return out.printList();
            case Parser.MARK:
                index = Parser.parseIndex(str);
                task = list.getTask(index);
                task.markAsDone();
                return out.printMark(task);
            case Parser.DELETE:
                index = Parser.parseIndex(str);
                task = list.deleteTask(index);
                return out.printDelete(task);
            case Parser.TODO:
                description = Parser.parseDescription(str);
                task = new Todo(description);
                list.addTask(task);
                return out.printAdd(task);
            case Parser.DEADLINE:
                result = Parser.parseDeadline(str);
                String byStr = result[1];
                description = result[0];
                task = new Deadline(description, LocalDate.parse(byStr));
                list.addTask(task);
                return out.printAdd(task);
            case Parser.EVENT:
                result = Parser.parseEvent(str);
                String fromStr = result[1];
                String toStr = result[2];
                description = result[0];
                task = new Event(description, LocalDate.parse(fromStr), LocalDate.parse(toStr));
                list.addTask(task);
                return out.printAdd(task);
            case Parser.FIND:
                description = Parser.parseDescription(str);
                ArrayList<Task> matches = list.matchTasks(description);
                return out.printFind(matches);
            case Parser.SORT:
                list.sort();
                return out.printSort();
            default:
                throw new UnknownCommandException();
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
        return "ERROR";
    }

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Zazu.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setZazu(this);

            stage.setOnCloseRequest(event -> {
                event.consume();
                handleExit();
            });

            this.list = new TaskList(Storage.loadTasks());
            this.out = new OutputFormatter(this.list);

            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
