package org.eneko.cart;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.eneko.prices.PriceRepository;
import org.eneko.prices.ProductPrice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.util.*;

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

    private Map<String, Set<String>> validOptionsByProductType = Collections.EMPTY_MAP;


    private static final DecimalFormat DF2 = new DecimalFormat(".##");

    //Calculated by (base_price + round(base_price * artist_markup)) * quantity
    public double calculatePrice(CartProduct cartProduct) {
        ProductPrice productPrice = priceRepository.
                findByProductTypeAndOptions(cartProduct.getProductType(),
                        cartProduct.getOptions(),
                        this.validOptionsByProductType.get(cartProduct.getProductType()));
        Double artist = Double.valueOf(DF2.format(productPrice.getBasePrice() * cartProduct.getArtistMarkup() * 0.01));
        double productSellingPrice = (productPrice.getBasePrice()
                + (artist))
                * cartProduct.getQuantity();
        return productSellingPrice;
    }

    public Iterable<ProductPrice> setupBasePrices(String basePricesFilename) {
        Collection<ProductPrice> pricesFromFile = priceFactory.createPricesFromFile(basePricesFilename);
        Iterable productPrices = priceRepository.save(pricesFromFile);
        validOptionsByProductType = getValidOptionsByProductType(pricesFromFile);
        return productPrices;
    }

    private Map<String, Set<String>> getValidOptionsByProductType(Collection<ProductPrice> pricesFromFile) {
        final Map<String, Set<String>> validOptionsByProductType = new HashMap<>();
        pricesFromFile.stream().forEach(productPrice -> {
            if (!validOptionsByProductType.containsKey(productPrice.getProductType())) {
                validOptionsByProductType.put(productPrice.getProductType(),
                        productPrice.getOptions().keySet());
            }
        });
        return validOptionsByProductType;
    }
}
