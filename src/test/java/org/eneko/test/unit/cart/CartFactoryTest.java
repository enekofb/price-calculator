package org.eneko.test.unit.cart;

import gherkin.lexer.Tr;
import org.eneko.test.unit.cart.Cart;
import org.eneko.test.unit.cart.CartFactory;
import org.eneko.test.unit.cart.utils.JsonValidator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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
