package org.eneko.test.unit.prices;

import org.eneko.prices.ProductPrice;
import org.eneko.test.unit.cart.PriceFactory;
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
import java.util.Collection;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

/**
 * Created by eneko on 23/06/17.
 */
@RunWith(SpringRunner.class)
public class PriceFactoryTest {

    @Mock
    private JsonValidator priceValidator;

    @InjectMocks
    private PriceFactory priceFactory;

    @Test
    public void canCreateNonEmptyProductPriceCollectionFromFile() throws IOException {
        String pricesFilename = "/unit/prices.json";
        when(priceValidator.isValid(pricesFilename)).thenReturn(Boolean.TRUE);
        Collection<ProductPrice> productPrices = priceFactory.createPricesFromFile(pricesFilename);
        assertEquals(10,productPrices.size());
    }

}