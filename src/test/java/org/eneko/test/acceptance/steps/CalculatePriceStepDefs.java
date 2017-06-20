package org.eneko.test.acceptance.steps;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.eneko.cart.Cart;
import org.eneko.cart.CartService;
import org.eneko.prices.pricecalculator.PriceCalculator;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertTrue;

/**
 * Created by eneko on 19/06/17.
 */
public class CalculatePriceStepDefs {

    private PriceCalculator priceCalculator;
    private Cart cart;
    private double cartPrice;

    private CartService cartService;

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
        cart = Cart.builder().build();
        assertThat(cart,is(notNullValue()));
    }

    @When("^I calculate its price$")
    public void i_calculate_its_price() throws Throwable {
        cartPrice = cart.caculatePrice();
        assertTrue(cartPrice >= 0.0);
    }

    @Then("^Its prices is (\\d+)$")
    public void its_prices_is(int expectedPrice) throws Throwable {
        assertTrue(cartPrice == expectedPrice);
    }

    @Given("^the prices by \"([^\"]*)\" and an cart by file \"([^\"]*)\"$")
    public void thePricesByAndAnCartByFile(String pricesFilename, String cartFilename) throws Throwable {
        Cart cart = cartService.newCartFromFile(cartFilename);
        cartPrice  = cartService.calculatePrice(cart);
        assertTrue(cartPrice >= 0.0);
    }
}
