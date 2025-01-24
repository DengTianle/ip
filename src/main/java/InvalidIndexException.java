public class InvalidIndexException extends ZazuException{
    private static final String ERROR_MESSAGE = "please enter a valid index. ";
    public InvalidIndexException(){
        super(ERROR_MESSAGE);
    }
}
