package zazu.data.exception;

public class ZazuException  extends Exception {
    public ZazuException(String message) {
        super("Error: " + message);
    }
}
