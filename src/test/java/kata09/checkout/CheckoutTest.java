package kata09.checkout;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Ignore;
import org.junit.Test;

public class CheckoutTest {
	
	/**
	 * This method and all testTotal* methods reflects the kata test requirements.
	 * @see <a href="http://codekata.com/kata/kata09-back-to-the-checkout/">Tescase TestPrice.test_totals</a>
	 * 
	 * @throws Exception
	 */
	private int price(String ... products) {
		Checkout current = new Checkout();
		for (String product : products) {
			current.scan(product);
		}
		return current.getTotal();
	}

	@Test
	@Ignore
	public void testTotal0() throws Exception {
		assertThat(price()).isEqualTo(0);
	}

	@Test
	@Ignore
	public void testTotalA() throws Exception {
		assertThat(price("A")).isEqualTo(50);
	}

	@Test
	@Ignore
	public void testTotalAB() throws Exception {
		assertThat(price("A", "B")).isEqualTo(80);
	}

	@Test
	@Ignore
	public void testTotalCDAB() throws Exception {
		assertThat(price("C", "D", "B", "A")).isEqualTo(115);
	}

	@Test
	@Ignore
	public void testTotal2A() throws Exception {
		assertThat(price("A", "A")).isEqualTo(100);
	}

	@Test
	@Ignore
	public void testTotal3A() throws Exception {
		assertThat(price("A", "A", "A")).isEqualTo(130);
	}

	@Test
	@Ignore
	public void testTotal4A() throws Exception {
		assertThat(price("A", "A", "A", "A")).isEqualTo(180);
	}

	@Test
	@Ignore
	public void testTotal5A() throws Exception {
		assertThat(price("A", "A", "A", "A", "A")).isEqualTo(230);
	}

	@Test
	@Ignore
	public void testTotal6A() throws Exception {
		assertThat(price("A", "A", "A", "A", "A", "A")).isEqualTo(260);
	}

	@Test
	@Ignore
	public void testTotal3A1B() throws Exception {
		assertThat(price("A", "A", "A", "B")).isEqualTo(160);
	}

	@Test
	@Ignore
	public void testTotal3A2B() throws Exception {
		assertThat(price("A", "A", "A", "B", "B")).isEqualTo(175);
	}

	@Test
	@Ignore
	public void testTotal3A2B1D() throws Exception {
		assertThat(price("A", "A", "A", "B", "B", "D")).isEqualTo(190);
	}

	@Test
	@Ignore
	public void testTotalDABABA() throws Exception {
		assertThat(price("D", "A", "B", "A", "B", "A")).isEqualTo(190);
	}
	
	@Test
	@Ignore
	public void testIncremental() throws Exception {
		Checkout checkout = new Checkout();
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
