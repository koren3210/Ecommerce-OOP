package exceptions;

public class NoItemsInCartException extends ECommerceException {
    public NoItemsInCartException(String message) {
        super(message);
    }

    public NoItemsInCartException() {
        super("The cart is empty. Cannot proceed with the payment.");
    }
}