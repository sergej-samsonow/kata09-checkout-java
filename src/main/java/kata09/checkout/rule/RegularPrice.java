package kata09.checkout.rule;

import java.util.HashMap;
import java.util.Map;

import kata09.checkout.RuleException;

public class RegularPrice {

	private Map<String, Integer> prices = new HashMap<>();

	public void add(String product, int price) {
		prices.put(product, price);
	}
	
	public int forProduct(String product) throws RuleException {
		if (! prices.containsKey(product)) {
			throw new RuleException("No price for product: " + product);
		}
		return prices.get(product);
	}
}
