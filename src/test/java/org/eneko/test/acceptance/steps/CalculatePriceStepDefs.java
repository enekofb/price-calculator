package org.eneko.test.acceptance.steps;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.eneko.prices.pricecalculator.PriceCalculator;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNull.notNullValue;

/**
 * Created by eneko on 19/06/17.
 */
public class CalculatePriceStepDefs {

    private PriceCalculator priceCalculator;


    @Given("^exists a price calculator with prices \"([^\"]*)\" and cart \"([^\"]*)\"$")
    public void exists_a_price_calculator_with_prices_and_cart(String pricesSchemaFilename, String cartSchemaFilename) throws Throwable {
        priceCalculator = PriceCalculator.builder().
                basePriceSchemaFilename(pricesSchemaFilename).
                cartSchemaFilename(cartSchemaFilename)
                .build();
        assertThat(priceCalculator,is(notNullValue()));
    }


    @Given("^I have an empty cart$")
    public void i_have_an_empty_cart() throws Throwable {
        Cart
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("^I calculate its price$")
    public void i_calculate_its_price() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^Its prices is (\\d+)$")
    public void its_prices_is(int arg1) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

}
