package org.eneko.cart;

import org.eneko.PriceCalculatorApplication;
import org.eneko.prices.PriceRepository;
import org.eneko.prices.ProductPrice;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

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

    PriceService priceService;

    @Test
    public void canCalculatePriceForProduct(){
        CartProduct product = mock(CartProduct.class);
        when(product.getProductType()).thenReturn("productType");
        when(product.getOptions()).thenReturn(Collections.emptyMap());
        when(product.getQuantity()).thenReturn(1);
        when(product.getArtistMarkup()).thenReturn(1);

        ProductPrice productPrice = mock(ProductPrice.class);
        when(productPrice.getBasePrice()).thenReturn(100);

        PriceRepository priceRepository = mock(PriceRepository.class);
        when(priceRepository.findByProductTypeAndOptions(
                product.getProductType(),
                product.getOptions(),
                Collections.emptySet())).thenReturn(productPrice);

        Map validOptionsByProductType = new HashMap<>();
        validOptionsByProductType.put("productType",Collections.emptySet());

        priceService = PriceService.builder()
                .validOptionsByProductType(validOptionsByProductType)
                .priceRepository(priceRepository)
                .build();
        double price = priceService.calculatePrice(product);

        assertThat("price is not 101 but "+price,price ==101);
    }

}