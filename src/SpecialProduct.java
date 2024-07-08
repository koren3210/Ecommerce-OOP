import enums.Category;

public class SpecialProduct extends Product {
    private double packagingCost; // Additional cost for special packaging

    public SpecialProduct(String name, double price, String sellerName, Category category, double packagingCost) {
        super(name, price, sellerName, category);
        this.packagingCost = packagingCost;
    }

    // Getter method to retrieve the packaging cost
    public double getPackagingCost() {
        return packagingCost;
    }

    @Override
    public double getTotalCost() {
        return getPrice() + packagingCost; // Total cost includes the packaging cost
    }

    @Override
    public String getDetails() {
        return super.toString() +
                String.format(", Packaging Cost: %.2f$", packagingCost) +
                String.format(", Total Cost: %.2f$", getTotalCost());
    }
}