package zazu.storage;

import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.google.gson.TypeAdapter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Custom adapter for serializing and deserializing {@link LocalDate} objects.
 * This adapter uses ISO_LOCAL_DATE format to convert {@link LocalDate} to a string and vice versa.
 */
public class LocalDateAdapter extends TypeAdapter<LocalDate> {

    /** The date formatter used to serialize and deserialize the date */
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE;

    /**
     * Writes the {@link LocalDate} to the {@link JsonWriter}.
     *
     * @param out The {@link JsonWriter} to which the {@link LocalDate} is written.
     * @param value The {@link LocalDate} to write.
     * @throws IOException If an I/O error occurs during writing.
     */
    @Override
    public void write(JsonWriter out, LocalDate value) throws IOException {
        if (value == null) {
            out.nullValue();
        } else {
            out.value(value.format(FORMATTER));
        }
    }

    /**
     * Reads a {@link LocalDate} from the {@link JsonReader}.
     *
     * @param in The {@link JsonReader} from which the {@link LocalDate} is read.
     * @return The {@link LocalDate} read from the input.
     * @throws IOException If an I/O error occurs during reading.
     */
    @Override
    public LocalDate read(JsonReader in) throws IOException {
        if (in.peek() == com.google.gson.stream.JsonToken.NULL) {
            in.nextNull();
            return null;
        }
        return LocalDate.parse(in.nextString(), FORMATTER);
    }
}
