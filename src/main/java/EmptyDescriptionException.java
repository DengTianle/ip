public class EmptyDescriptionException extends ZazuException{
    private static final String ERROR_MESSAGE = "please enter a known command. ";
    public EmptyDescriptionException(){
        super(ERROR_MESSAGE);
    }
}

