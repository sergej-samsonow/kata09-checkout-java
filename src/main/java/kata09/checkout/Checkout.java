package kata09.checkout;

import java.util.ArrayList;
import java.util.List;

public class Checkout {

	private Integer total;
	private Rules rules;
	private List<String> products;

	public Checkout(Rules rules) {
		this.rules = rules;
		this.products = new ArrayList<>();
	}

	public void scan(String product) {
		total = null;
		products.add(product);
	}

	public int getTotal() throws RuleException {
		if (total == null) {
			total = rules.calculate(products);
		}
		return total;
	}

}
