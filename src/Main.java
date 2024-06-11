import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ECommerceManager manager = new ECommerceManager();
        Scanner scanner = new Scanner(System.in);

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
                    System.out.print("Enter seller's username: ");
                    String sellerUsername = scanner.next();
                    System.out.print("Enter seller's password: ");
                    String sellerPassword = scanner.next();
                    manager.addSeller(sellerUsername, sellerPassword);
                    break;
                case 2:
                    System.out.print("Enter buyer's username: ");
                    String buyerUsername = scanner.next();
                    System.out.print("Enter buyer's password: ");
                    String buyerPassword = scanner.next();
                    System.out.print("Enter buyer's address: ");
                    String buyerAddress = scanner.next();
                    manager.addBuyer(buyerUsername, buyerPassword, buyerAddress);
                    break;
                case 3:
                    System.out.print("Enter seller's username: ");
                    String sellerName = scanner.next();
                    System.out.print("Enter product name: ");
                    String productName = scanner.next();
                    System.out.print("Enter product price: ");
                    double productPrice = scanner.nextDouble();
                    manager.addProductForSeller(sellerName, productName, productPrice);
                    break;
                case 4:
                    System.out.print("Enter buyer's username: ");
                    String buyerName = scanner.next();
                    System.out.print("Enter seller's username: ");
                    String sellerNameForBuyer = scanner.next();
                    manager.displaySellerProducts(sellerNameForBuyer);
                    System.out.print("Enter product name: ");
                    String productNameForBuyer = scanner.next();
                    manager.addProductForBuyerCart(buyerName, productNameForBuyer, sellerNameForBuyer);
                    break;
                case 5:
                    System.out.print("Enter buyer's username for payment: ");
                    String buyerNameForPayment = scanner.next();
                    if (!manager.displayCurrentShoppingCart(buyerNameForPayment)) {
                        System.out.println("No items in the cart. Returning to the menu.");
                        break;
                    }
                    System.out.print("Are you want to pay for this cart? (y/n)");
                    String paymentDecision = scanner.next();
                    manager.paymentForShoppingCart(paymentDecision, buyerNameForPayment);
                    break;
                case 6:
                    manager.displayBuyers();
                    break;
                case 7:
                    manager.displaySellers();
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        } while (continueLoop);

        scanner.close();
    }
}
