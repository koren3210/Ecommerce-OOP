import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        ECommerceManager manager = new ECommerceManager();

        // demo data for debugging
        setupDemoData(manager);

        int choice;
        boolean continueLoop = true;

        do {
            // Displaying the menu options
            System.out.println("Menu:");
            System.out.println("0. Exit");
            System.out.println("1. Add Seller");
            System.out.println("2. Add Buyer");
            System.out.println("3. Add Product for Seller");
            System.out.println("4. Add Product for Buyer");
            System.out.println("5. Order Payment for Buyer");
            System.out.println("6. Display Details of All Buyers");
            System.out.println("7. Display Details of All Sellers");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt(); // Getting the user's choice

            switch (choice) {
                case 0:
                    continueLoop = false;
                    break;
                case 1:
                    addSeller(manager);
                    break;
                case 2:
                    addBuyer(manager);
                    break;
                case 3:
                    addProductForSeller(manager);
                    break;
                case 4:
                    addProductForBuyer(manager);
                    break;
                case 5:
                    orderPaymentForBuyer(manager);
                    break;
                case 6:
                    displayDetailsOfAllBuyers(manager);
                    break;
                case 7:
                    displayDetailsOfAllSellers(manager);
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        } while (continueLoop);

        scanner.close();
    }

    private static void setupDemoData(ECommerceManager manager) {
        // Adding sellers
        manager.addSeller("seller1", "password1");
        manager.addSeller("seller2", "password2");

        // Adding buyers
        manager.addBuyer("buyer1", "password1", "123 New york, City A");
        manager.addBuyer("buyer2", "password2", "456 Tel Aviv, City B");
        manager.addBuyer("buyer3", "password3", "456 Thailand, City B");

        // Adding products for sellers
        manager.addProductForSeller("seller1", "Product A", 10.99);
        manager.addProductForSeller("seller1", "Product B", 15.49);
        manager.addProductForSeller("seller2", "Product C", 20.99);
        manager.addProductForSeller("seller2", "Product D", 5.99);

        // Adding products for buyers (adding to cart)
        manager.addProductForBuyerCart("buyer1", "Product A", "seller1");
        manager.addProductForBuyerCart("buyer1", "Product C", "seller2");
        manager.addProductForBuyerCart("buyer2", "Product B", "seller1");
    }

    private static void addSeller(ECommerceManager manager) {
        boolean usernameExists;

        do {
            System.out.print("Enter seller's username: ");
            String sellerUsername = scanner.next();

            if (manager.findSeller(sellerUsername) != null) {
                System.out
                        .println("Username " + sellerUsername + " already exists! Please choose a different username.");
                usernameExists = true; // set flag to true to repeat the loop
            } else {
                usernameExists = false; // set flag to false to exit the loop
                System.out.print("Enter seller's password: ");
                String sellerPassword = scanner.next();
                manager.addSeller(sellerUsername, sellerPassword);
            }
        } while (usernameExists);

    }

    private static void addBuyer(ECommerceManager manager) {
        boolean usernameExists = false;

        do {
            System.out.print("Enter buyer's username: ");
            String buyerUsername = scanner.next();

            if (manager.findBuyer(buyerUsername) != null) {
                System.out.println("Username " + buyerUsername + " already exists! Please enter a different username.");
                usernameExists = true;
            } else {
                usernameExists = false;
                System.out.print("Enter buyer's password: ");
                String buyerPassword = scanner.next();
                System.out.print("Enter buyer's address: ");
                scanner.nextLine();
                String buyerAddress = scanner.nextLine();
                manager.addBuyer(buyerUsername, buyerPassword, buyerAddress);
            }
        } while (usernameExists);
    }

    private static void addProductForSeller(ECommerceManager manager) {
        System.out.print("Enter seller's username: ");
        String sellerName = scanner.next();
        System.out.print("Enter product name: ");
        String productName = scanner.next();
        System.out.print("Enter product price: ");
        double productPrice = scanner.nextDouble();
        manager.addProductForSeller(sellerName, productName, productPrice);
    }

    private static void addProductForBuyer(ECommerceManager manager) {
        System.out.print("Enter buyer's username: ");
        String buyerUsername = scanner.next();
        System.out.print("Enter seller's username: ");
        String sellerNameForBuyer = scanner.next();
        manager.displaySellerProducts(sellerNameForBuyer);
        System.out.print("Enter product name: ");
        scanner.nextLine();
        String productNameForBuyer = scanner.nextLine();
        manager.addProductForBuyerCart(buyerUsername, productNameForBuyer, sellerNameForBuyer);
    }

    private static void orderPaymentForBuyer(ECommerceManager manager) {
        System.out.print("Enter buyer's username for payment: ");
        String buyerUsernameForPayment = scanner.next();
        if (!manager.displayCurrentShoppingCart(buyerUsernameForPayment)) {
            System.out.println("No items in the cart. Returning to the menu.");
            return;
        }

        System.out.print("Are you want to pay for this cart? (y/n)");
        String paymentDecision = scanner.next();
        manager.paymentForShoppingCart(paymentDecision, buyerUsernameForPayment);
    }

    private static void displayDetailsOfAllBuyers(ECommerceManager manager) {
        manager.displayBuyers();
    }

    private static void displayDetailsOfAllSellers(ECommerceManager manager) {
        manager.displaySellers();
    }
}
