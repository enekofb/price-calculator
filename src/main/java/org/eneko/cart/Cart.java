package org.eneko.cart;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by eneko on 20/06/2017.
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Cart {

    private Collection<CartProduct> products;

    public int getNumProducts() {
        return products.size();
    }

    public double calculatePrice() {
        return 0.0;
    }

    public Collection<CartProduct> getProducts() {
        return products;
    }
}
