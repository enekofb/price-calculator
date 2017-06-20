package org.eneko.cart;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collection;
import java.util.Collections;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;


/**
 * Created by eneko on 21/06/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CartServiceTest {

    @Mock
    CartProduct cartProduct;

    @Mock
    PriceService priceService;

    @Autowired
    CartService cartService;

    @Test
    public void canCalculatePriceForEmptyCart(){
        Cart cart = Cart.builder().products(Collections.emptyList()).build();
        assertThat("Calculated price for cart empty",cartService.calculatePrice(cart)==0.0);
    }

    @Test
    public void canCalculatePriceForSingleProductCart(){
        Cart cart = Cart.builder().products(Collections.singletonList(cartProduct)).build();
        assertThat("Calculated price for cart empty",cart.calculatePrice()==1.0);
    }

}