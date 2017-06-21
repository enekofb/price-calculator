package org.eneko.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by eneko on 20/06/17.
 */
@Component
public class CartService {

    @Autowired
    PriceService priceService;

    @Autowired
    CartFactory cartFactory;

    public double calculatePrice(Cart cart) {
        return cart.getProducts().stream().
                map(product -> priceService.calculatePrice(product)).
                reduce(0.0, Double::sum);
    }

    public Cart newCartFromFile(String cartFilename) {

        return null;
    }
}
