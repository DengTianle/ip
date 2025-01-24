public class ZazuException  extends Exception {
    public ZazuException(String message) {
        super("Error: " + message);
    }
}
