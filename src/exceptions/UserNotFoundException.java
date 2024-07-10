package exceptions;

public class UserNotFoundException extends ECommerceException {
    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException() {
        super("User not found.");
    }
}