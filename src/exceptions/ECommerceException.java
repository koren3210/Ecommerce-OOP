package exceptions;

public class ECommerceException extends Exception {
    public ECommerceException(String message) {
        super(message);
    }

    public ECommerceException() {
        super("An error occurred in the E-Commerce system.");
    }
}
