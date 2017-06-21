package org.eneko.cart;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.eneko.cart.utils.JsonValidator;
import org.eneko.prices.PriceRepository;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;

/**
 * Created by eneko on 21/06/17.
 */
@NoArgsConstructor
//@Component
public class PriceService {

    private PriceRepository prices;

    public PriceService(String priceSchemaFileName,String pricesFileName){
        boolean isValid = false;
        try {
            isValid = new JsonValidator(priceSchemaFileName).isValid(pricesFileName);
        } catch (IOException e) {
            throw new RuntimeException("Invalid prices schema, cannot create price service");
        }

        if(!isValid)
            throw new RuntimeException("Invalid prices schema, cannot create price service");

//        prices = createPrices(pricesFileName);
    }

    private Map<String,Double> createPrices(String pricesFileName) {
        return Collections.emptyMap();
    }

    public double calculatePrice(CartProduct cartProduct) {
        return 0.0;
    }
}
