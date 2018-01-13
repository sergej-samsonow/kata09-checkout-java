package kata09.checkout.rule;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class TotalPriceTest {

    @Test
    public void testGetTotalDefault0() throws Exception {
        TotalPrice price = new TotalPrice();
        assertThat(price.getTotal()).isEqualTo(0);
    }

    @Test
    public void testAdd() throws Exception {
        TotalPrice price = new TotalPrice();
        price.add(10);
        assertThat(price.getTotal()).isEqualTo(10);
    }

    @Test
    public void testAddTwoTimes() throws Exception {
        TotalPrice price = new TotalPrice();
        price.add(10);
        price.add(20);
        assertThat(price.getTotal()).isEqualTo(30);
    }

}
