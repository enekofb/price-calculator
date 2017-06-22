package org.eneko.test.unit.prices;

import org.eneko.cart.Cart;
import org.eneko.cart.CartFactory;
import org.eneko.cart.utils.JsonValidator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by eneko on 20/06/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CartFactoryTest {

    @Autowired
    private CartFactory cartFactory;

    @Test
    public void canCreateEmptyCart(){
        Cart cart = cartFactory.newCart();
        assertThat("has one element",cart.getNumProducts()==0);
    }

    @Test
    public void canCreateCartFromFileWithOneElement(){
        String singleElementCartFilename = "/cart-4560.json";
        Cart cart = cartFactory.newCartFromFile(singleElementCartFilename);
        assertThat("has one element",cart.getNumProducts()==1);
    }
}
