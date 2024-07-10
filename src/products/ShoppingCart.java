package products;

import java.util.Arrays;
import java.util.Date;

public class ShoppingCart implements Cloneable {
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

    // Getter methods
    public Product[] getCart() {
        return cart;
    }

    public int getCartSize() {
        return productCount;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public void addProduct(Product product) {
        if (productCount == cart.length) {
            expandArray();
        }
        cart[productCount++] = product;
        totalAmount += product.getTotalCost();
    }

    public void clearCart() {
        cart = new Product[INITIAL_SIZE];
        productCount = 0;
        totalAmount = 0;
        purchaseDate = null;
    }

    // Helper method to expand the size of the array
    private void expandArray() {
        int newSize = cart.length * GROWTH_FACTOR;
        cart = Arrays.copyOf(cart, newSize);
    }

    // Override the clone method to clone a shopping cart
    @Override
    public Object clone() throws CloneNotSupportedException {
        ShoppingCart clonedCart = (ShoppingCart) super.clone();
        clonedCart.cart = new Product[cart.length];
        for (int i = 0; i < cart.length; i++) {
            if (cart[i] != null) {
                clonedCart.cart[i] = (Product) cart[i].clone();
            }
        }
        return clonedCart;
    }

    @Override
    public String toString() {
        String cartDetails = "Shopping Cart: \n";

        // Iterate over the products in the cart
        for (int i = 0; i < productCount; i++) {
            if (cart[i] != null) {
                cartDetails += "\t" + cart[i] + "\n";
            }
        }

        // Add total amount and purchase date to cart details
        cartDetails += "\t" + String.format("Total Amount: $%.2f", totalAmount) + "\n";
        cartDetails += "\t" + "Purchase Date: " + (purchaseDate != null ? purchaseDate : "Not purchased yet..") + "\n";

        return cartDetails;
    }
}
