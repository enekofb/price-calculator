package org.eneko.cart;

import org.eneko.prices.PriceRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


/**
 * Created by eneko on 21/06/17.
 */
@RunWith(SpringRunner.class)
//@SpringBootTest
public class PriceServiceTest {


    @Mock
    PriceRepository priceRepository;

    @InjectMocks
    PriceService priceService;

    @Test
    public void canCreatePriceServiceWithSchemaAndPrices(){
        String schema = "/unit/schema.json";
        String prices = "/unit/prices.json";
        PriceService priceService = new PriceService(schema,prices);
        assertThat(priceService,notNullValue());
    }

    @Test
    public void canCalculatePriceForProduct(){
        CartProduct product = mock(CartProduct.class);
        when(priceRepository.getPrice(product)).thenReturn(1.0);
        double productPrice = priceService.calculatePrice(product);
        assertThat("cannot calculate price",productPrice ==1.0);
    }

}