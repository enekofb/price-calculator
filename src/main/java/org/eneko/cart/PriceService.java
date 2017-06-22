package org.eneko.cart;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.eneko.prices.PriceRepository;
import org.eneko.prices.ProductPrice;
import org.eneko.prices.ProductPriceOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * Created by eneko on 21/06/17.
 */
@NoArgsConstructor
@AllArgsConstructor
@Component
@Builder
public class PriceService {

    @Autowired
    private PriceRepository priceRepository;

    @Autowired
    private PriceFactory priceFactory;


    public int calculatePrice(CartProduct cartProduct) {
        adaptCartProductToPriceProduct(cartProduct);
//        ProductPrice productPrice =priceRepository
//                findProductByProductTypeAndProperties(cartProduct.getProductType(),cartProduct.getAdditionalProperties());
//        //TODO:The *price calculation* for one item is as follows: (base_price + round(base_price * artist_markup)) * quantity
//        return productPrice.getBasePrice();
        return 0;
    }

    private ProductPrice adaptCartProductToPriceProduct(CartProduct cartProduct) {
        ProductPriceOptions priceOptions = ProductPriceOptions.builder().additionalProperties(cartProduct.getOptions().getAdditionalProperties()).build();
        return ProductPrice.builder().productType(cartProduct.getProductType()).options(priceOptions).build();
    }

    public Iterable<ProductPrice> setupBasePrices(String basePricesFilename) {
        Collection<ProductPrice> pricesFromFile = priceFactory.createPricesFromFile(basePricesFilename);
        return priceRepository.save(pricesFromFile);
    }
}
