package org.eneko.test.unit.prices;

import org.eneko.prices.pricecalculator.PriceCalculator;
import org.junit.Test;

//import static com.sun.org.apache.xerces.internal.util.PropertyState.is;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNull.notNullValue;

/**
 * Created by eneko on 19/06/17.
 */
public class PriceCalculatorTest {

    PriceCalculator priceCalculator;


    @Test
    public void canCreatePriceCalculator(){
        priceCalculator =PriceCalculator.builder().build();
        assertThat(priceCalculator,is(notNullValue()));
    }

    @Test
    public void canCreatePriceCalculatorWithCartAndPricesSchemas(){
        String pricesSchemaFilename = "prices-schema.json";
        String cartSchemaFilename = "cart-schema.json";
        priceCalculator =PriceCalculator.builder().
                basePriceSchemaFilename(pricesSchemaFilename).
                cartSchemaFilename(cartSchemaFilename).
                build();
        assertThat(priceCalculator,is(notNullValue()));
        assertThat(priceCalculator.getBasePriceSchemaFilename(),is(pricesSchemaFilename));
        assertThat(priceCalculator.getCartSchemaFilename(),is(cartSchemaFilename));
    }



}