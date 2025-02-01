package zazu.data.exception;

public class EmptyDescriptionException extends ZazuException{
    private static final String ERROR_MESSAGE = "please enter a nonempty description. ";
    public EmptyDescriptionException(){
        super(ERROR_MESSAGE);
    }
}

