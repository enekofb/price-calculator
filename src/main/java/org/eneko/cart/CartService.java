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

    public double calculatePrice(Cart cart) {
        return 0;
    }
}
