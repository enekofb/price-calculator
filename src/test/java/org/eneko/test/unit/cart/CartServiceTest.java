package org.eneko.test.unit.cart;

import org.eneko.cart.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Collections;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


/**
 * Created by eneko on 21/06/17.
 */
@RunWith(SpringRunner.class)
public class CartServiceTest {

    private Logger LOG = LoggerFactory.getLogger(CartServiceTest.class);

    @Mock
    Cart cart;

    @Mock
    CartFactory cartFactory;

    @Mock
    PriceService priceService;

    @InjectMocks
    CartService cartService;

    @Test
    public void canCreateCart(){
        String singleElementCartFilename = "dummyJson";
        Cart cart = cartFactory.newCartFromFile(singleElementCartFilename);
        when(cartFactory.newCartFromFile(singleElementCartFilename)).thenReturn(cart);
        Cart createdCart = cartService.newCartFromFile(singleElementCartFilename);
        assertThat("created cart",cart == createdCart);
    }

    @Test
    public void canCalculatePriceForSingleProductCart(){
        CartProduct cartProduct = mock(CartProduct.class);
        when(priceService.calculatePrice(cartProduct)).thenReturn(10);
        when(cart.getProducts()).thenReturn(Collections.singletonList(cartProduct));
        double cartPrice = cartService.calculatePrice(cart);
        assertThat("Invalid price calculated",cartPrice==10);
    }

    @Test
    public void canCalculatePriceForMultipleProductCart(){
        CartProduct cartProduct = mock(CartProduct.class);
        CartProduct cartProduct2 = mock(CartProduct.class);
        CartProduct cartProduct3 = mock(CartProduct.class);
        when(priceService.calculatePrice(cartProduct)).thenReturn(1);
        when(priceService.calculatePrice(cartProduct2)).thenReturn(2);
        when(priceService.calculatePrice(cartProduct3)).thenReturn(3);
        when(cart.getProducts()).thenReturn(Arrays.asList(cartProduct,cartProduct2,cartProduct3));
        double cartPrice = cartService.calculatePrice(cart);
        assertThat("Invalid price calculated",cartPrice==6.0);
    }

}