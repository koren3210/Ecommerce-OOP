import java.util.Arrays;
import java.util.Date;

public class ShoppingCart {
    // Constants for initial size of the cart and the growth factor for resizing
    private final int INITIAL_SIZE = 4;
    private final int GROWTH_FACTOR = 2;

    private Product[] cart;
    private int productCount;
    private double totalAmount;
    private Date purchaseDate;

    public ShoppingCart() {
        this.cart = new Product[INITIAL_SIZE];
        this.productCount = 0;
    }

    // Getter method to retrieve the cart
    public Product[] getCart() {
        return cart;
    }

    // Getter method to retrieve the size of the cart
    public int getCartSize() {
        return productCount;
    }

    // Method to clear the cart
    public void clearCart() {
        cart = new Product[INITIAL_SIZE]; // Reset cart to initial size
        productCount = 0; // Reset product count
        totalAmount = 0; // Reset total amount
        purchaseDate = null; // Reset purchase date
    }

    // Getter method to retrieve the total amount
    public double getTotalAmount() {
        return totalAmount;
    }

    // Getter method to retrieve the purchase date
    public Date getPurchaseDate() {
        return purchaseDate;
    }

    // Setter method to set the purchase date
    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    // Method to add a product to the cart
    public void addProduct(Product product) {
        if (productCount == cart.length) {
            expandArray(); // Expand the array if it's full
        }
        cart[productCount++] = product; // Add the product to the cart
        totalAmount += product.getPrice(); // Update the total amount
    }

    // Helper method to expand the size of the array
    private void expandArray() {
        int newSize = cart.length * GROWTH_FACTOR; // Calculate the new size
        cart = Arrays.copyOf(cart, newSize); // Create a new array with the new size
    }

    // Method to represent the cart as a string
    @Override
    public String toString() {
        String cartDetails = "Shopping Cart: \n"; // Start with cart header

        // Iterate over the products in the cart
        for (int i = 0; i < productCount; i++) {
            if (cart[i] != null) {
                cartDetails += "\t" + cart[i] + "\n";
            }
        }

        // Add total amount and purchase date to cart details
        cartDetails += "\t" + "Total Amount: " + totalAmount + "$\n";
        cartDetails += "\t" + "Purchase Date: " + (purchaseDate != null ? purchaseDate : "Not purchased yet..") + "\n";

        return cartDetails; // Return the cart details as string
    }
}
