package org.eneko.test.acceptance.steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.eneko.PriceCalculatorApplication;
import org.eneko.test.unit.cart.Cart;
import org.eneko.test.unit.cart.CartService;
import org.eneko.test.unit.cart.PriceService;
import org.eneko.prices.ProductPrice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertTrue;

/**
 * Created by eneko on 19/06/17.
 */
@SpringBootTest(classes = PriceCalculatorApplication.class)
@ContextConfiguration(
        classes = PriceCalculatorApplication.class,
        loader = SpringApplicationContextLoader.class)
@IntegrationTest
public class CalculatePriceStepDefs {

    @Autowired
    private CartService cartService;

    @Autowired
    private PriceService priceService;

    private Cart cart;

    private int cartPrice;

    @Given("^a price service the following base prices \"([^\"]*)\"$")
    public void a_price_service_the_following_base_prices(String basePricesFilename) throws Throwable {
        Iterable<ProductPrice> productPrices = priceService.setupBasePrices("/acceptance/prices/"+basePricesFilename);
        productPrices.forEach( productPrice -> assertThat(productPrice.getProductType(),notNullValue()));
    }

    @Given("^I have a cart given by \"([^\"]*)\"$")
    public void i_have_a_cart_given_by_cart_json(String cartFilename) throws Throwable {
        cart = cartService.newCartFromFile("/acceptance/carts/"+cartFilename);
        assertThat(cart,notNullValue());
        }

    @Given("^I have an empty cart$")
    public void i_have_an_empty_cart() throws Throwable {
        cart = cartService.newCart();
        assertThat(cart,is(notNullValue()));
    }

    @When("^I calculate its price$")
    public void i_calculate_its_price() throws Throwable {
        cartPrice = cartService.calculatePrice(cart);
        assertTrue(cartPrice >= 0);
    }

    @Then("^Its prices is \"([^\"]*)\"$")
    public void its_prices_is(String expectedPrice) throws Throwable {
        assertTrue(cartPrice == Double.valueOf(expectedPrice));
    }
}
