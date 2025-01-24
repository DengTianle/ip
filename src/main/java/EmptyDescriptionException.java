public class EmptyDescriptionException extends ZazuException{
    private static final String ERROR_MESSAGE = "please enter an nonempty description. ";
    public EmptyDescriptionException(){
        super(ERROR_MESSAGE);
    }
}

