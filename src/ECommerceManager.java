import java.util.Arrays;

public class ECommerceManager {
    // Constants for initial size of the arrays and the growth factor for resizing
    private static final int INITIAL_SIZE = 4;
    private static final int GROWTH_FACTOR = 2;

    // Arrays to store sellers and buyers
    private Seller[] sellers = new Seller[INITIAL_SIZE];
    private Buyer[] buyers = new Buyer[INITIAL_SIZE];

    // Counters to keep track of the number of sellers and buyers
    private int sellersCount = 0;
    private int buyersCount = 0;

    // Method to add a new seller
    public void addSeller(String username, String password) {
        // Check if the seller already exists
        if (findSeller(username) != null) {
            System.out.println("Seller already exists!");
            return;
        }

        // Expand the sellers array if it's full
        if (sellersCount == sellers.length) {
            expandArray(sellers);
        }

        // Add the new seller and increment the seller count
        sellers[sellersCount++] = new Seller(username, password);
        System.out.println("Seller added successfully!");
    }

    // Method to add a new buyer
    public void addBuyer(String username, String password, String address) {
        // Check if the buyer already exists
        if (findBuyer(username) != null) {
            System.out.println("Buyer already exists!");
            return;
        }

        // Expand the buyers array if it's full
        if (buyersCount == buyers.length) {
            expandArray(buyers);
        }

        // Add the new buyer and increment the buyer count
        buyers[buyersCount++] = new Buyer(username, password, address);
        System.out.println("Buyer added successfully!");
    }

    // Method to display products for a specific seller
    public void displaySellerProducts(String sellerUsername) {
        // Find the seller by username
        Seller seller = findSeller(sellerUsername);
        if (seller == null) {
            System.out.println("Seller not found!");
            return;
        }

        System.out.println("Products for Seller: " + sellerUsername);
        Product[] products = seller.getProducts();

        // Print each product of the seller
        for (int i = 0; i < products.length; i++) {
            if (products[i] != null) {
                System.out.println((i + 1) + ". " + products[i]);
            }
        }
    }

    // Method to add a product to a buyer's cart from a specific seller
    public void addProductForBuyerCart(String buyerUsername, String productName, String sellerNameForBuyer) {
        // Find the buyer and seller by their usernames
        Buyer buyer = findBuyer(buyerUsername);
        if (buyer == null) {
            System.out.println("Buyer not found!");
            return;
        }

        Seller seller = findSeller(sellerNameForBuyer);
        if (seller == null) {
            System.out.println("Seller not found!");
            return;
        }

        Product[] sellerProducts = seller.getProducts();
        if (sellerProducts == null || sellerProducts.length == 0) {
            System.out.println("No products available for the specified seller!");
            return;
        }

        // Check if the product with the given name exists in the seller's products
        Product productToAdd = null;
        for (Product product : sellerProducts) {
            if (product != null && product.getName().equals(productName)) {
                productToAdd = product;
                break;
            }
        }

        // If the product is not found, print a message and return
        if (productToAdd == null) {
            System.out.println("Product '" + productName + "' not found for seller: " + sellerNameForBuyer);
            return;
        }

        // Add the product to the buyer's shopping cart
        buyer.addToShoppingCart(productToAdd);
        System.out.println("Product added to cart successfully for buyer: " + buyerUsername);
    }

    // Method to add a product for a specific seller
    public void addProductForSeller(String sellerUsername, String productName, double price) {
        // Find the seller by username
        Seller seller = findSeller(sellerUsername);
        if (seller == null) {
            System.out.println("Seller not found!");
            return;
        }

        // Add the new product to the seller's product list
        seller.addProduct(new Product(productName, price, sellerUsername));
        System.out.println("Product added successfully for seller: " + sellerUsername);
    }

    // Method to display the current shopping cart of a buyer
    public boolean displayCurrentShoppingCart(String buyerNameForPayment) {
        // Find the buyer by username
        Buyer currentBuyer = findBuyer(buyerNameForPayment);
        if (currentBuyer == null) {
            System.out.println("Buyer not found!");
            return false;
        }

        ShoppingCart currentCart = currentBuyer.getShoppingCart();
        int cartSize = currentCart.getCartSize();
        Product[] products = currentCart.getCart();
        double totalAmount = currentCart.getTotalAmount();

        // If the cart is empty, print a message
        if (cartSize == 0) {
            System.out.println("\tCart is Empty!");
            return false;
        } else {
            // Print the products in the cart and the total amount
            System.out.println("Products in Cart:");
            for (Product product : products) {
                if (product != null) {
                    System.out.println("\t" + product);
                }
            }
            System.out.println("Total Amount: $" + totalAmount);
            return true;
        }
    }

    // Method to handle payment for the shopping cart
    public void paymentForShoppingCart(String paymentDecision, String buyerNameForPayment) {
        paymentDecision = paymentDecision.toLowerCase();
        Buyer currentBuyer = findBuyer(buyerNameForPayment);

        // If the payment decision is 'n', print a message and return
        if (paymentDecision.equals("n")) {
            System.out.println("Payment has not been made");
            return;
        } else if (paymentDecision.equals("y")) {
            // If the payment decision is 'y', process the payment
            currentBuyer.paymentForShoppingCart();
            System.out.println("Successful payment\n");
        }
    }

    // Helper method to find a seller by username
    private Seller findSeller(String username) {
        for (int i = 0; i < sellersCount; i++) {
            if (sellers[i].getUsername().equals(username)) {
                return sellers[i];
            }
        }
        return null;
    }

    // Helper method to find a buyer by username
    private Buyer findBuyer(String username) {
        for (int i = 0; i < buyersCount; i++) {
            if (buyers[i].getUserName().equals(username)) {
                return buyers[i];
            }
        }
        return null;
    }

    // Method to display all buyers
    public void displayBuyers() {
        if (buyersCount == 0) {
            System.out.println("No existing Buyers!");
            return;
        }
        for (int i = 0; i < buyersCount; i++) {
            System.out.println(i + 1 + "." + "Buyer name: " + buyers[i].getUserName());
            System.out.println("Address: " + buyers[i].getAddress());
            System.out.println("Current Shopping Cart:");

            ShoppingCart cart = buyers[i].getShoppingCart();
            int cartSize = cart.getCartSize();
            Product[] products = cart.getCart();
            double totalAmount = cart.getTotalAmount();
            ShoppingCart[] ordersHistory = buyers[i].getOrdersHistory();
            int ordersHistoryCount = buyers[i].getOrderHistoryCount();

            // Print the products in the cart
            if (cartSize == 0) {
                System.out.println("Cart is Empty!");
            } else {
                for (Product product : products) {
                    if (product != null) {
                        System.out.println("\t" + product);
                    }
                }
            }
            System.out.println("Total Amount: $" + totalAmount);

            // Print the order history
            System.out.println("Orders history: ");
            if (ordersHistoryCount == 0) {
                System.out.println("No orders history..");
            } else {
                for (ShoppingCart shoppingCart : ordersHistory) {
                    if (shoppingCart != null) {
                        System.out.println("\t" + shoppingCart);
                    }
                }
            }
        }
    }

    // Method to display all sellers
    public void displaySellers() {
        if (sellersCount == 0) {
            System.out.println("No existing Sellers!");
            return;
        }
        for (int i = 0; i < sellersCount; i++) {
            System.out.println(i + 1 + "." + "Seller name:" + sellers[i].getUsername());
            System.out.println("Products:");
            for (Product product : sellers[i].getProducts()) {
                if (product != null) {
                    System.out.println("\t" + product);
                }
            }
        }
    }

    // Helper method to expand the size of an array
    private void expandArray(Object[] array) {
        int newSize = array.length * GROWTH_FACTOR;
        array = Arrays.copyOf(array, newSize);
    }
}
