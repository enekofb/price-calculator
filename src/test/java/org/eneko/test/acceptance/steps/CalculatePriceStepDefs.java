package org.eneko.test.acceptance.steps;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.eneko.cart.Cart;
import org.eneko.cart.CartFactory;
import org.eneko.cart.CartService;
import org.eneko.cart.PriceService;
import org.eneko.prices.pricecalculator.PriceCalculator;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertTrue;

/**
 * Created by eneko on 19/06/17.
 */
public class CalculatePriceStepDefs {

    private CartService cartService;

    private PriceService priceService;

    private Cart cart;

    private double cartPrice;


    @Given("^a price service with schema \"([^\"]*)\" and base prices \"([^\"]*)\"$")
    public void a_price_service_with_schema_and_base_prices(String priceSchemaFilename, String pricesFilename) throws Throwable {
        priceService = new PriceService(priceSchemaFilename,pricesFilename);
        assertThat(priceService,notNullValue());
    }

    @Given("^a cart service with schema \"([^\"]*)\"$")
    public void a_cart_service_with_schema(String arg1) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Given("^I have an cart by file \"([^\"]*)\"$")
    public void i_have_an_cart_by_file(String arg1) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }


    @Given("^I have an empty cart$")
    public void i_have_an_empty_cart() throws Throwable {
        cart = Cart.builder().build();
        assertThat(cart,is(notNullValue()));
    }

    @When("^I calculate its price$")
    public void i_calculate_its_price() throws Throwable {
        cartPrice = cartService.calculatePrice(cart);
        assertTrue(cartPrice >= 0.0);
    }

    @Then("^Its prices is (\\d+)$")
    public void its_prices_is(int expectedPrice) throws Throwable {
        assertTrue(cartPrice == expectedPrice);
    }

    @Given("^the prices by \"([^\"]*)\" and an cart by file \"([^\"]*)\"$")
    public void thePricesByAndAnCartByFile(String pricesFilename, String cartFilename) throws Throwable {
//        priceService.setBasePricesFromFrile(pricesFilename);
//        cartService.setPriceService(priceService);
        Cart cart = cartService.newCartFromFile(cartFilename);
        double cartPrice  = cartService.calculatePrice(cart);
        assertTrue(cartPrice >= 0.0);
    }
}
