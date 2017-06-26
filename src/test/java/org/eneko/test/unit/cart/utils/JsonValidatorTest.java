package org.eneko.test.unit.cart.utils;

import org.eneko.cart.utils.JsonValidator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by eneko on 20/06/17.
 */
@RunWith(SpringRunner.class)
public class JsonValidatorTest {

    private JsonValidator jsonValidator;

    @Before
    public  void setup(){
        String jsonSchemaFile = "/unit/cart-schema.json";
        jsonValidator = new JsonValidator(jsonSchemaFile);
    }

    @Test
    public void canValidateJsonFile() throws IOException {
        String jsonSFile = "/unit/cart-4560.json";
        assertThat("validating json file",jsonValidator.isValid(jsonSFile));
    }

}