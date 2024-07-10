package products;

import enums.Category;

public abstract class Product implements Cloneable {
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

    // Getter methods
    public int getSerialNumber() {
        return serialNumber;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getSellerName() {
        return sellerName;
    }

    public Category getCategory() {
        return category;
    }

    public abstract String getDetails();

    public abstract double getTotalCost();

    // Override the clone method to clone a product
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return "ID: " + serialNumber +
                ", Product name: " + name +
                ", Price: " + price + "$" +
                ", Seller Name: " + sellerName +
                ", Category: " + category;
    }
}
