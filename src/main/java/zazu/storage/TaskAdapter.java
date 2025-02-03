package zazu.storage;

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

/**
 * Custom adapter for serializing and deserializing {@link Task} objects.
 * This adapter is used to handle polymorphic {@link Task} types (e.g., {@link Todo}, {@link Deadline}, {@link Event}).
 */
public class TaskAdapter implements JsonDeserializer<Task>, JsonSerializer<Task> {

    /**
     * Deserializes a {@link Task} object from its JSON representation.
     *
     * @param json The JSON element to deserialize.
     * @param typeOfT The type of the object to deserialize.
     * @param context The deserialization context.
     * @return The deserialized {@link Task} object.
     * @throws JsonParseException If the JSON cannot be parsed into a {@link Task}.
     */
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

    /**
     * Serializes a {@link Task} object to its JSON representation.
     *
     * @param task The {@link Task} object to serialize.
     * @param typeOfSrc The type of the object to serialize.
     * @param context The serialization context.
     * @return The JSON element representing the {@link Task}.
     */
    @Override
    public JsonElement serialize(Task task, Type typeOfSrc, JsonSerializationContext context) {
        return context.serialize(task);
    }
}