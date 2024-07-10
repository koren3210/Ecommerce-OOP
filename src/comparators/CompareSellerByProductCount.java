package comparators;

import java.util.Comparator;
import users.Seller;

public class CompareSellerByProductCount implements Comparator<Seller> {
    @Override
    public int compare(Seller s1, Seller s2) {
        return Integer.compare(s1.getProducts().length, s2.getProducts().length);
    }
}
