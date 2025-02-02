package zazu.data.exception;

/**
 * Custom exception class for handling Zazu-specific errors.
 * This class extends {@link Exception} and provides a constructor that
 * prepends "Error: " to the provided error message.
 */
public class ZazuException extends Exception {

    /**
     * Constructs a new {@code ZazuException} with the specified detail message.
     * The message is prepended with "Error: ".
     *
     * @param message the detail message, which is saved for later retrieval
     * by the {@link Throwable#getMessage()} method.
     */
    public ZazuException(String message) {
        super("Error: " + message);
    }
}
