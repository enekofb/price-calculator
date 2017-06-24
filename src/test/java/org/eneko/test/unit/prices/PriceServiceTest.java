package org.eneko.test.unit.prices;

import org.eneko.prices.PriceRepository;
import org.eneko.prices.ProductPrice;
import org.eneko.test.unit.cart.CartProduct;
import org.eneko.test.unit.cart.PriceService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


/**
 * Created by eneko on 21/06/17.
 */
@RunWith(SpringRunner.class)
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
        int price = priceService.calculatePrice(product);

        assertThat("price is not 101 but "+price,price ==101);
    }

}