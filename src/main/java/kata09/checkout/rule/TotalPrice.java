package kata09.checkout.rule;

import static java.lang.Math.addExact;

public class TotalPrice {
    private int total;
    
    public void add(int price) {
        total = addExact(total, price);
    }
    
    public int getTotal() {
        return total;
    }
}