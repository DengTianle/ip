package zazu.data.exception;

/**
 * Exception thrown when an unknown command is encountered.
 * This class extends {@link ZazuException} and provides a default error message
 * indicating that the user should enter a known command.
 */
public class UnknownCommandException extends ZazuException {

    /** The default error message for this exception */
    private static final String ERROR_MESSAGE = "please enter a known command. ";

    /**
     * Constructs a new {@code UnknownCommandException} with the default error message.
     */
    public UnknownCommandException() {
        super(ERROR_MESSAGE);
    }
}
