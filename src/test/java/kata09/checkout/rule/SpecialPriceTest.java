package kata09.checkout.rule;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class SpecialPriceTest {

    private SpecialPrice price;

    @Before
    public void init() {
        price = new SpecialPrice();
    }

    private Map<String, Integer> amount(String product, int value) {
        Map<String, Integer> result = new HashMap<>();
        result.put(product, value);
        return result;
    }

    @Test
    public void testGroupProductsEmptyList() throws Exception {
        assertThat(price.groupProducts(asList())).isEmpty();
    }

    @Test
    public void testGroupProductsOneElement() throws Exception {
        assertThat(price.groupProducts(asList("A"))).isEqualTo(asList(amount("A", 1)));
    }

    @Test
    public void testGroupProductsTwoElements() throws Exception {
        assertThat(price.groupProducts(asList("A", "A"))).isEqualTo(asList(amount("A", 2)));
    }

    @Test
    public void testGroupProductsThreeElements() throws Exception {
        assertThat(price.groupProducts(asList("A", "A", "B"))).isEqualTo(asList(amount("A", 2), amount("B", 1)));
    }

    @Test
    public void testGroupProductsSpeical1() throws Exception {
        price.add("A", 1, 40);
        assertThat(price.groupProducts(asList("A"))).isEqualTo(asList(amount("A", 1)));
    }

    @Test
    public void testGroupProductsSpeical2to1() throws Exception {
        price.add("A", 2, 40);
        assertThat(price.groupProducts(asList("A"))).isEqualTo(asList(amount("A", 1)));
    }

    @Test
    public void testGroupProductsSpeical2to2() throws Exception {
        price.add("A", 2, 40);
        assertThat(price.groupProducts(asList("A", "A"))).isEqualTo(asList(amount("A", 2)));
    }

    @Test
    public void testGroupProductsSpeical2to3() throws Exception {
        price.add("A", 2, 40);
        assertThat(price.groupProducts(asList("A", "A", "A"))).isEqualTo(asList(amount("A", 2), amount("A", 1)));
    }

    @Test
    public void testGroupProductsSpeical2to4() throws Exception {
        price.add("A", 2, 40);
        assertThat(price.groupProducts(asList("A", "A", "A", "A"))).isEqualTo(asList(amount("A", 2), amount("A", 2)));
    }

    @Test
    public void testGroupProductsSpeical2and3to4() throws Exception {
        price.add("A", 2, 40);
        price.add("A", 3, 55);
        assertThat(price.groupProducts(asList("A", "A", "A", "A"))).isEqualTo(asList(amount("A", 3), amount("A", 1)));
    }

    @Test
    public void testGroupProductsSpeical2and3to5() throws Exception {
        price.add("A", 2, 40);
        price.add("A", 3, 55);
        assertThat(price.groupProducts(asList("A", "A", "A", "A", "A")))
                .isEqualTo(asList(amount("A", 3), amount("A", 2)));
    }

    @Test
    public void testGroupProductsSpeical2and3to6() throws Exception {
        price.add("A", 2, 40);
        price.add("A", 3, 55);
        assertThat(price.groupProducts(asList("A", "A", "A", "A", "A", "A")))
                .isEqualTo(asList(amount("A", 3), amount("A", 3)));
    }

    @Test
    public void testGroupProductsSpeical2and3to7() throws Exception {
        price.add("A", 2, 40);
        price.add("A", 3, 55);
        assertThat(price.groupProducts(asList("A", "A", "A", "A", "A", "A", "A")))
                .isEqualTo(asList(amount("A", 3), amount("A", 3), amount("A", 1)));
    }

    @Test
    public void testGroupProductsSpeical2and3to8() throws Exception {
        price.add("A", 2, 40);
        price.add("A", 3, 55);
        assertThat(price.groupProducts(asList("A", "A", "A", "A", "A", "A", "A", "A")))
                .isEqualTo(asList(amount("A", 3), amount("A", 3), amount("A", 2)));
    }

    @Test
    public void testGroupProductsSpeical4and2to7() throws Exception {
        price.add("A", 4, 40);
        price.add("A", 2, 55);
        assertThat(price.groupProducts(asList("A", "A", "A", "A", "A", "A", "A")))
                .isEqualTo(asList(amount("A", 4), amount("A", 2), amount("A", 1)));
    }

    @Test
    public void testGroupProductsSpeical4and2to8() throws Exception {
        price.add("A", 4, 40);
        price.add("A", 2, 55);
        assertThat(price.groupProducts(asList("A", "A", "A", "A", "A", "A", "A", "A")))
                .isEqualTo(asList(amount("A", 4), amount("A", 4)));
    }

    @Test
    public void testGroupProductsSpeical4and2to8and2b() throws Exception {
        price.add("A", 4, 40);
        price.add("A", 2, 55);
        assertThat(price.groupProducts(asList("A", "A", "A", "A", "A", "A", "A", "A", "B", "B")))
                .isEqualTo(asList(amount("A", 4), amount("A", 4), amount("B", 2)));
    }

    @Test
    public void testForProductRegularPrice() throws Exception {
        assertThat(price.forProduct("A", 1, 10)).isEqualTo(10);
    }

    @Test
    public void testForProductRegularPriceByAmount() throws Exception {
        assertThat(price.forProduct("A", 2, 10)).isEqualTo(20);
    }

    @Test
    public void testForProductSpecialPrice() throws Exception {
        price.add("A", 2, 15);
        assertThat(price.forProduct("A", 2, 10)).isEqualTo(15);
    }
}
