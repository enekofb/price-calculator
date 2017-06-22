package org.eneko.cart;

import org.eneko.PriceCalculatorApplication;
import org.eneko.prices.PriceRepository;
import org.eneko.prices.ProductPrice;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


/**
 * Created by eneko on 21/06/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PriceCalculatorApplication.class)
public class PriceServiceTest {

    @Autowired
    PriceRepository priceRepository;

    PriceService priceService;

    @Test
    public void canCalculatePriceForProduct(){
        CartProduct product = mock(CartProduct.class);
        ProductPrice productPrice = mock(ProductPrice.class);
        priceService = PriceService.builder().priceRepository(priceRepository).build();
        when(product.getProductType()).thenReturn("productType");
        when(product.getAdditionalProperties()).thenReturn(Collections.emptyMap());
        when(productPrice.getBasePrice()).thenReturn(10);
        when(priceRepository.findProductByProductTypeAndProperties(product.getProductType(),
                product.getAdditionalProperties())).thenReturn(productPrice);
        int price = priceService.calculatePrice(product);
        assertThat("cannot calculate price",price ==10);
    }

}