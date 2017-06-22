package org.eneko.cart;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.eneko.cart.utils.JsonValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

/**
 * Created by eneko on 20/06/2017.
 */
@NoArgsConstructor
@AllArgsConstructor
@Component
public class CartFactory {

    @Autowired
    @Qualifier("cartValidator")
    private JsonValidator cartValidator;

    public Cart newCartFromFile(String cartFilename) {
        try {
            cartValidator.isValid(cartFilename);
        } catch (IOException e) {
            throw new IllegalArgumentException("Invalid json file");
        }
        return readCartFromFile(cartFilename);
    }

    private Cart readCartFromFile(String cartFilename) {
        try (InputStream inputStream = getClass().getResourceAsStream(cartFilename)) {
            List<CartProduct> cartProducts = new ObjectMapper().readValue(inputStream, new TypeReference<List<CartProduct>>(){});
            return Cart.builder().products(cartProducts).build();
        } catch (IOException e) {
            throw new RuntimeException("Cannot read Cart from file",e);
        }
    }

    public Cart newCart() {
        return Cart.builder().products(Collections.emptyList()).build();
    }
}
