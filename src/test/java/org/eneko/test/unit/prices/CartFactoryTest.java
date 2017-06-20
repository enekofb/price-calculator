package org.eneko.test.unit.prices;

import org.eneko.cart.Cart;
import org.eneko.cart.CartFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by eneko on 20/06/2017.
 */
public class CartFactoryTest {

    @Autowired
    CartFactory factory;


    @Test
    public void canCreateCartFromFileWithOneElement(){
        String singleElementCartFilename = "cart-4560.json";
        Cart cart = factory.newCartFromFile(singleElementCartFilename);
        assertThat("has one element",cart.getNumProducts()==1);
    }
}
