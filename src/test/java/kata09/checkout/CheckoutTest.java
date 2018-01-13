package kata09.checkout;

import static org.assertj.core.api.Assertions.assertThat;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.Test;

import kata09.checkout.rule.RegularPrice;
import kata09.checkout.rule.SpecialPrice;

public class CheckoutTest {

	private Rules rules() throws Exception {
		Rules rules = new Rules();
		rules.setProcessing(new String(Files.readAllBytes(Paths.get(getClass().getResource("/script.source").toURI()))));
		RegularPrice regular = new RegularPrice();
		regular.add("A", 50);
		regular.add("B", 30);
		regular.add("C", 20);
		regular.add("D", 15);
		
		SpecialPrice special = new SpecialPrice();
		special.add("A", 3, 130);
		special.add("B", 2, 45);
		
		rules.addRule("regularPrice", regular);
		rules.addRule("specialPrice", special);
		return rules;
	}
	
	/**
	 * This method and all testTotal* methods reflects the kata test requirements.
	 * @see <a href="http://codekata.com/kata/kata09-back-to-the-checkout/">Tescase TestPrice.test_totals</a>
	 * 
	 * @throws Exception
	 */
	private int price(String ... products) throws Exception {
		Checkout current = new Checkout(rules());
		for (String product : products) {
			current.scan(product);
		}
		return current.getTotal();
	}

	@Test
	public void testTotal0() throws Exception {
		assertThat(price()).isEqualTo(0);
	}

	@Test
	public void testTotalA() throws Exception {
		assertThat(price("A")).isEqualTo(50);
	}

	@Test
	public void testTotalAB() throws Exception {
		assertThat(price("A", "B")).isEqualTo(80);
	}

	@Test
	public void testTotalCDAB() throws Exception {
		assertThat(price("C", "D", "B", "A")).isEqualTo(115);
	}

	@Test
	public void testTotal2A() throws Exception {
		assertThat(price("A", "A")).isEqualTo(100);
	}

	@Test
	public void testTotal3A() throws Exception {
		assertThat(price("A", "A", "A")).isEqualTo(130);
	}

	@Test
	public void testTotal4A() throws Exception {
		assertThat(price("A", "A", "A", "A")).isEqualTo(180);
	}

	@Test
	public void testTotal5A() throws Exception {
		assertThat(price("A", "A", "A", "A", "A")).isEqualTo(230);
	}

	@Test
	public void testTotal6A() throws Exception {
		assertThat(price("A", "A", "A", "A", "A", "A")).isEqualTo(260);
	}

	@Test
	public void testTotal3A1B() throws Exception {
		assertThat(price("A", "A", "A", "B")).isEqualTo(160);
	}

	@Test
	public void testTotal3A2B() throws Exception {
		assertThat(price("A", "A", "A", "B", "B")).isEqualTo(175);
	}

	@Test
	public void testTotal3A2B1D() throws Exception {
		assertThat(price("A", "A", "A", "B", "B", "D")).isEqualTo(190);
	}

	@Test
	public void testTotalDABABA() throws Exception {
		assertThat(price("D", "A", "B", "A", "B", "A")).isEqualTo(190);
	}
	
	@Test
	public void testIncremental() throws Exception {
		Checkout checkout = new Checkout(rules());
		assertThat(checkout.getTotal()).isEqualTo(0);
		
		checkout.scan("A");
		assertThat(checkout.getTotal()).isEqualTo(50);

		checkout.scan("B");
		assertThat(checkout.getTotal()).isEqualTo(80);

		checkout.scan("A");
		assertThat(checkout.getTotal()).isEqualTo(130);

		checkout.scan("A");
		assertThat(checkout.getTotal()).isEqualTo(160);

		checkout.scan("B");
		assertThat(checkout.getTotal()).isEqualTo(175);
	}
}
