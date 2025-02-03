package zazu.storage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import zazu.data.task.Task;

import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.Writer;
import java.io.Reader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

/**
 * Utility class for saving and loading tasks to and from a file.
 * This class uses Gson to handle serialization and deserialization of task data.
 */
public class Storage {

    /** The file name used for saving and loading tasks */
    private static final String FILE_NAME = "tasks.json";

    /** The Gson instance for serializing and deserializing tasks */
    private static final Gson GSON = new GsonBuilder()
            .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
            .registerTypeAdapter(Task.class, new TaskAdapter())
            .setPrettyPrinting()
            .create();

    /**
     * Saves the given list of tasks to a file.
     *
     * @param taskList The list of tasks to save to the file.
     */
    public static void saveTasks(List<Task> taskList) {
        try (Writer writer = new FileWriter(FILE_NAME)) {
            GSON.toJson(taskList, writer);
        } catch (IOException e) {
            System.err.println("Error saving task list: " + e.getMessage());
        } catch (JsonIOException | JsonSyntaxException e) {
            System.err.println("Error in the file: " + e.getMessage());
        }
    }

    /**
     * Loads the list of tasks from the file.
     *
     * @return An {@link ArrayList} of tasks loaded from the file, or an
     * empty list if no tasks exist or if an error occurs.
     */
    public static ArrayList<Task> loadTasks() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            return new ArrayList<>();
        }

        try (Reader reader = new FileReader(file)) {
            Type listType = new TypeToken<ArrayList<Task>>() {}.getType();
            return GSON.fromJson(reader, listType);
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("File corrupted. Starting with an empty task list.");
        }

        return new ArrayList<>();
    }
}
