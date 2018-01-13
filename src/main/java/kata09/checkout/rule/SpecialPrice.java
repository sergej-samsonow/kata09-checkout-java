package kata09.checkout.rule;

import static java.lang.Math.subtractExact;
import static java.util.Collections.emptyMap;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeMap;

public class SpecialPrice {
    
    private Map<String, Map<Integer, Integer>> prices = new HashMap<>();

    public void add(String product, Integer amount, Integer price) {
        prices.computeIfAbsent(product, v -> new TreeMap<>()).put(amount, price);
    }
    
    private Collection<Integer> splitAmount(Integer incoming, Set<Integer> possible) {
    	int amount = incoming;
    	ArrayList<Integer> result = new ArrayList<>();
    	for(Integer current : possible) {
    		if (amount == current) {
    			result.add(amount);
    			amount = 0;
    		}
    		else if (amount > current) {
    			int mod = amount % current;
    			int count = (subtractExact(amount, mod)) /current;
    			amount = mod;
				for (int i = 0; i < count; i ++) {
					result.add(current);
				}
    		}
    		
    		// stop iteration no other groups is possible
    		if (amount == 0) {
    			return result;
    		}
    	}
		result.add(amount);
		return result;
	}
    
    public List<Map<String, Integer>> groupProducts(Collection<String> products) {
    	List<Map<String, Integer>> result = new ArrayList<>();
    	Map<String, Integer> byAmount = new HashMap<>();
    	for (String product : products) {
    		int amount = byAmount.computeIfAbsent(product, v -> 0);
    		byAmount.put(product, amount + 1);
		}
    	for (Entry<String, Integer> entry : byAmount.entrySet()) {
    		String product = entry.getKey();
    		Integer amount = entry.getValue();
    		Map<Integer, Integer> special = prices.computeIfAbsent(product, v -> emptyMap());

    		// we have no special prices for product
    		if (special.isEmpty()) {
    			Map<String, Integer> current = new HashMap<>();
    			current.put(product, amount);
    			result.add(current);
    		}
    		else {
    			// group by amount descending order (higher amount first)
    			for (Integer splited : splitAmount(amount, ((NavigableSet<Integer>)special.keySet()).descendingSet())) {
    				Map<String, Integer> current = new HashMap<>();
    				current.put(product, splited);
    				result.add(current);
				}
    		}
		}
    	return result;
    }


	public int forProduct(String product, Integer amount, Integer regularPrice) {
        return prices.getOrDefault(product, emptyMap()).getOrDefault(amount, amount * regularPrice);
    }
}
