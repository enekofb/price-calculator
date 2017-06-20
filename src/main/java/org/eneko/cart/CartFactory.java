package org.eneko.cart;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.eneko.cart.utils.JsonValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.stereotype.Component;

import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;

/**
 * Created by eneko on 20/06/2017.
 */
@Component
public class CartFactory {

    JsonValidator cartValidator = new JsonValidator("/cart-schema.json");

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
            return new ObjectMapper()
                    .readValue(inputStream,Cart.class);
        } catch (IOException e) {
            throw new RuntimeException("Cannot read Cart from file");
        }
    }
}
