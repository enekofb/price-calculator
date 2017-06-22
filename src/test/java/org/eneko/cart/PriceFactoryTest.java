package org.eneko.cart;

import org.eneko.prices.ProductPrice;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collection;

import static org.junit.Assert.*;

/**
 * Created by eneko on 23/06/17.
 */
@RunWith(SpringRunner.class)
public class PriceFactoryTest {

    private PriceFactory priceFactory;

    @Before
    public void setup() {
        priceFactory = new PriceFactory();
    }

    @Test
    public void canCreateNonEmptyProductPriceCollectionFromFile() {
        String pricesFilename = "/unit/prices.json";
        Collection<ProductPrice> productPrices = priceFactory.createPricesFromFile(pricesFilename);
        assertEquals(10,productPrices.size());
    }

}