package zazu.data.exception;

/**
 * Exception thrown to inform the UI of exception in providing response.
 * This does not inherit ZazuException because this is only used internally.
 */
public class ResponseException extends Exception {
    /**
     * Constructs a new {@code ResponseException}.
     */
    public ResponseException(String message) {
        super(message);
    }
}
