package zazu.data.exception;

public class UnknownCommandException extends ZazuException{
    private static final String ERROR_MESSAGE = "please enter an nonempty description. ";

    public UnknownCommandException() {
        super(ERROR_MESSAGE);
    }
}
