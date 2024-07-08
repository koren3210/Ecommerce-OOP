import enums.Category;

public class StandardProduct extends Product {

    public StandardProduct(String name, double price, String sellerName, Category category) {
        super(name, price, sellerName, category);
    }

    @Override
    public double getTotalCost() {
        return getPrice();
    }

    @Override
    public String getDetails() {
        return toString();
    }
}
