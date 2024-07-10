package users;

import java.util.Arrays;
import java.util.Date;

import products.Product;
import products.ShoppingCart;

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

    public void setShoppingCart(ShoppingCart newCart) {
        this.shoppingCart = newCart;
    }

    // Method to add a product to the shopping cart
    public void addToShoppingCart(Product product) {
        shoppingCart.addProduct(product);
    }

    // Method to process payment for the shopping cart
    public void paymentForShoppingCart() {
        Date purchaseDate = new Date();
        ShoppingCart purchasedCart = new ShoppingCart();
        Product[] cartProducts = shoppingCart.getCart();
        for (Product product : cartProducts) {
            if (product != null) {
                try {
                    purchasedCart.addProduct((Product) product.clone());
                } catch (CloneNotSupportedException e) {
                    System.out.println("Failed to clone the product: " + e.getMessage());
                }
            }
        }
        purchasedCart.setPurchaseDate(purchaseDate);
        if (ordersHistoryCount == ordersHistory.length) {
            expandArray();
        }
        ordersHistory[ordersHistoryCount++] = purchasedCart;
        shoppingCart.clearCart();
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
