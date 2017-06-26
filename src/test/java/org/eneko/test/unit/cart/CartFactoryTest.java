package org.eneko.test.uni.cart;

import org.eneko.cart.Cart;
import org.eneko.cart.CartFactory;
import org.eneko.cart.utils.JsonValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

/**
 * Created by eneko on 20/06/2017.
 */
@RunWith(SpringRunner.class)
public class CartFactoryTest {

    @Mock
    private JsonValidator cartValidator;

    @InjectMocks
    private CartFactory cartFactory;

    @Test
    public void canCreateEmptyCart(){
        Cart cart = cartFactory.newCart();
        assertThat("not empty cart",cart.getNumProducts()==0);
    }

    @Test
    public void canCreateCartFromFileWithOneElement() throws IOException {
        String singleElementCartFilename = "/unit/cart-4560.json";
        when(cartValidator.isValid(singleElementCartFilename)).thenReturn(Boolean.TRUE);
        Cart cart = cartFactory.newCartFromFile(singleElementCartFilename);
        assertThat("has more than one element",cart.getNumProducts()==1);
    }
}
