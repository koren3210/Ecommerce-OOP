import enums.Category;

public abstract class Product {
    private static int nextSerialNumber = 1;

    protected int serialNumber;
    protected String name; // Name of the product
    protected double price; // Price of the product
    protected String sellerName; // Name of the seller
    protected Category category;

    public Product(String name, double price, String sellerName, Category category) {
        this.serialNumber = nextSerialNumber++;
        this.name = name;
        this.price = price;
        this.sellerName = sellerName;
        this.category = category;
    }

    // Getter method to retrieve the serial number
    public int getSerialNumber() {
        return serialNumber;
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

    public Category getCategory() {
        return category;
    }

    public abstract String getDetails();

    public abstract double getTotalCost();

    // Override toString method to provide a string representation of the product
    @Override
    public String toString() {
        return "ID: " + serialNumber +
                ", Product name: " + name +
                ", Price: " + price + "$" +
                ", Seller Name: " + sellerName +
                ", Category: " + category;
    }
}
