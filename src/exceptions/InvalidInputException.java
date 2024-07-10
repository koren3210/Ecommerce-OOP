package exceptions;

public class InvalidInputException extends ECommerceException {
    public InvalidInputException(String message) {
        super(message);
    }

    public InvalidInputException() {
        super("Invalid input provided.");
    }
}