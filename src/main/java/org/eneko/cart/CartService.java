package org.eneko.cart;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by eneko on 20/06/17.
 */
@NoArgsConstructor
@AllArgsConstructor
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
        return cartFactory.newCartFromFile(cartFilename);
    }

    public Cart newCart() {
        return cartFactory.newCart();
    }
}
