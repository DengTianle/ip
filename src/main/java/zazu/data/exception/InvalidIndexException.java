package zazu.data.exception;

/**
 * Exception thrown when an invalid index is encountered.
 * This class extends {@link ZazuException} and provides a default error message
 * indicating that the user should enter a valid index.
 */
public class InvalidIndexException extends ZazuException {

    /** The default error message for this exception */
    private static final String ERROR_MESSAGE = "please enter a valid index. ";

    /**
     * Constructs a new {@code InvalidIndexException} with the default error message.
     */
    public InvalidIndexException() {
        super(ERROR_MESSAGE);
    }
}
