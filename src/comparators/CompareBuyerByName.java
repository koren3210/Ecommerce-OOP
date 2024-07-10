package comparators;

import java.util.Comparator;
import users.Buyer;

public class CompareBuyerByName implements Comparator<Buyer> {
    @Override
    public int compare(Buyer b1, Buyer b2) {
        return b1.getUsername().compareTo(b2.getUsername());
    }
}
