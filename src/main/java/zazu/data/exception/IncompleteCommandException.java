package zazu.data.exception;

/**
 * Exception thrown when a command is incomplete.
 * This class extends {@link ZazuException} and allows a custom error message to be provided.
 */
public class IncompleteCommandException extends ZazuException {

    /**
     * Constructs a new {@code IncompleteCommandException} with the specified detail message.
     *
     * @param message the detail message, which is saved for later retrieval
     * by the {@link Throwable#getMessage()} method.
     */
    public IncompleteCommandException(String message) {
        super(message);
    }
}
