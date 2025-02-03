package zazu.storage;//import com.google.gson.*;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.JsonObject;
import zazu.data.task.Deadline;
import zazu.data.task.Event;
import zazu.data.task.Task;
import zazu.data.task.Todo;

import java.lang.reflect.Type;


public class TaskAdapter implements JsonDeserializer<Task>, JsonSerializer<Task> {
    @Override
    public Task deserialize(JsonElement json, Type typeOfT,
            JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        String type = jsonObject.get("type").getAsString(); // Identify the subclass

        switch (type) {
            case "todo":
                return context.deserialize(json, Todo.class);
            case "deadline":
                return context.deserialize(json, Deadline.class);
            case "event":
                return context.deserialize(json, Event.class);
            default:
                throw new JsonParseException("Unknown task type: " + type);
        }
    }

    @Override
    public JsonElement serialize(Task task, Type typeOfSrc, JsonSerializationContext context) {
        return context.serialize(task);
    }
}