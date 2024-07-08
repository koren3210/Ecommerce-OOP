import java.util.Arrays;
import java.util.Date;

public class Buyer extends User {
    // Constants for initial size of orders history array and the growth factor for
    // resizing
    private final int INITIAL_SIZE = 4;
    private final int GROWTH_FACTOR = 2;

    private Address address;
    private ShoppingCart shoppingCart;
    private ShoppingCart[] ordersHistory;
    private int ordersHistoryCount;

    // Constructor updated to accept Address object
    public Buyer(String username, String password, Address address) {
        super(username, password);
        this.address = address;
        this.shoppingCart = new ShoppingCart();
        this.ordersHistory = new ShoppingCart[INITIAL_SIZE];
        this.ordersHistoryCount = 0;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    // Method to add a product to the shopping cart
    public void addToShoppingCart(Product product) {
        shoppingCart.addProduct(product);
    }

    // Method to process payment for the shopping cart
    public void paymentForShoppingCart() {
        Date purchaseDate = new Date();
        ShoppingCart purchasedCart = new ShoppingCart(); // Create a new ShoppingCart object
        Product[] cartProducts = shoppingCart.getCart(); // Get the products from the current cart
        for (Product product : cartProducts) {
            if (product instanceof SpecialProduct) {
                SpecialProduct specialProduct = (SpecialProduct) product;
                purchasedCart.addProduct(new SpecialProduct(
                        specialProduct.getName(),
                        specialProduct.getPrice(),
                        specialProduct.getSellerName(),
                        specialProduct.getCategory(),
                        specialProduct.getPackagingCost()));
            } else if (product instanceof StandardProduct) {
                StandardProduct standardProduct = (StandardProduct) product;
                purchasedCart.addProduct(new StandardProduct(
                        standardProduct.getName(),
                        standardProduct.getPrice(),
                        standardProduct.getSellerName(),
                        standardProduct.getCategory()));
            }
            purchasedCart.setPurchaseDate(purchaseDate); // Set the purchase date
            // Expand the orders history array if needed
            if (ordersHistoryCount == ordersHistory.length) {
                expandArray();
            }
            ordersHistory[ordersHistoryCount++] = purchasedCart; // Store the ShoppingCart object in orders history
            shoppingCart.clearCart(); // Clear the current shopping cart
        }
    }

    // Method to retrieve the orders history
    public ShoppingCart[] getOrdersHistory() {
        return ordersHistory;
    }

    // Method to retrieve the count of orders in the history
    public int getOrderHistoryCount() {
        return ordersHistoryCount;
    }

    // Helper method to expand the size of the orders history array
    private void expandArray() {
        int newSize = ordersHistory.length * GROWTH_FACTOR;
        ordersHistory = Arrays.copyOf(ordersHistory, newSize);
    }
}
