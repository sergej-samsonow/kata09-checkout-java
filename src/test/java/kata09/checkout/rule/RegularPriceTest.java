package kata09.checkout.rule;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import kata09.checkout.RuleException;

public class RegularPriceTest {

	@Test
	public void testForProduct() throws Exception {
		RegularPrice price = new RegularPrice();
		price.add("A", 10);
		assertThat(price.forProduct("A")).isEqualTo(10);
	}

	@Test(expected = RuleException.class)
	public void testForProductNotStored() throws Exception {
		RegularPrice price = new RegularPrice();
		assertThat(price.forProduct("A")).isEqualTo(10);
	}
}
