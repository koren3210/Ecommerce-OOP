public class Product {
    private String name; // Name of the product
    private double price; // Price of the product
    private String sellerName; // Name of the seller

    public Product(String name, double price, String sellerName) {
        this.name = name;
        this.price = price;
        this.sellerName = sellerName;
    }

    // Getter method to retrieve the product name
    public String getName() {
        return name;
    }

    // Getter method to retrieve the product price
    public double getPrice() {
        return price;
    }

    // Getter method to retrieve the seller name
    public String getSellerName() {
        return sellerName;
    }

    // Override toString method to provide a string representation of the product
    @Override
    public String toString() {
        return "Product name: " + name +
                ", price: " + price + "$" +
                ", sellerName: " + sellerName;
    }
}
