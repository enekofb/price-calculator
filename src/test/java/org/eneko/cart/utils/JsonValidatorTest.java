package org.eneko.cart.utils;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by eneko on 20/06/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class JsonValidatorTest {

    private JsonValidator jsonValidator;

    @Before
    public  void setup(){
        String jsonSchemaFile = "/cart-schema.json";
        jsonValidator = new JsonValidator(jsonSchemaFile);
    }

    @Test
    public void canValidateJsonFile() throws IOException {
        String jsonSFile = "/cart-4560.json";
        assertThat("validating json file",jsonValidator.isValid(jsonSFile));
    }

}