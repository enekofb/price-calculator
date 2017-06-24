package org.eneko.test.unit.cart;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NoArgsConstructor;
import org.eneko.test.unit.cart.utils.JsonValidator;
import org.eneko.prices.ProductPrice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.List;

/**
 * Created by eneko on 23/06/17.
 */
@Component
@NoArgsConstructor
public class PriceFactory {

    @Autowired
    @Qualifier("priceValidator")
    JsonValidator priceValidator;

    public Collection<ProductPrice> createPricesFromFile(String pricesFileName) {

        boolean isValid = false;
        try {
            isValid = priceValidator.isValid(pricesFileName);
        } catch (IOException e) {
            throw new RuntimeException("Invalid prices schema, cannot create price service");
        }

        if(!isValid)
            throw new RuntimeException("Invalid prices schema, cannot create price service");

        try (InputStream inputStream = getClass().getResourceAsStream(pricesFileName)) {
                return new ObjectMapper().readValue(inputStream, new TypeReference<List<ProductPrice>>(){});
            } catch (IOException e) {
                throw new RuntimeException("Cannot read product prices from file",e);
            }
    }
}
