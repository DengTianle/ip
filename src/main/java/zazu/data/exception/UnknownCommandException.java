package zazu.data.exception;

public class UnknownCommandException extends ZazuException{
    private static final String ERROR_MESSAGE = "please enter a known command. ";

    public UnknownCommandException() {
        super(ERROR_MESSAGE);
    }
}
