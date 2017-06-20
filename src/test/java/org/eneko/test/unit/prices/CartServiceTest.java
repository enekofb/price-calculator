package org.eneko.test.unit.prices;

import org.eneko.cart.Cart;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.stereotype.Component;

/**
 * Created by eneko on 20/06/17.
 */
public class CartServiceTest {

    @Mock
    CartFactory cartFactory;



    @Test
    public Cart canCreateCartFromFile(String cartFilename) {
        when(cartFactory.)
        Cart.builder().fromFile(cartFilename)
        assertThat(priceCalculator,is(notNullValue()));

        return null;
    }
}
