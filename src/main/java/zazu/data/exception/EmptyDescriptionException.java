package zazu.data.exception;

/**
 * Exception thrown when an empty description is provided.
 * This class extends {@link ZazuException} and provides a default error message
 * prompting the user to enter a nonempty description.
 */
public class EmptyDescriptionException extends ZazuException {

    /** The default error message for this exception */
    private static final String ERROR_MESSAGE = "please enter a nonempty description. ";

    /**
     * Constructs a new {@code EmptyDescriptionException} with the default error message.
     */
    public EmptyDescriptionException() {
        super(ERROR_MESSAGE);
    }
}
