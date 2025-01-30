import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.Writer;
import java.io.Reader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {
    private static final String FILE_NAME = "tasks.json";
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    public static void saveTasks(List<Task> taskList) {
        try (Writer writer = new FileWriter(FILE_NAME)) {
            GSON.toJson(taskList, writer);
        } catch (IOException e) {
            System.err.println("Error saving task list: " + e.getMessage());
        }
    }

    public static ArrayList<Task> loadTasks() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            return new ArrayList<>();
        }

        try (Reader reader = new FileReader(file)) {
            Type listType = new TypeToken<ArrayList<Task>>() {}.getType();
            return GSON.fromJson(reader, listType);
        } catch (IOException e) {
            System.err.println("Error reading task list: " + e.getMessage());
        } catch (com.google.gson.JsonSyntaxException e) {
            System.err.println("File corrupted. Starting with an empty task list.");
        }

        return new ArrayList<>();
    }
}
