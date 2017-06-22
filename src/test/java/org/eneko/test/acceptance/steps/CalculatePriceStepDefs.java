package org.eneko.test.acceptance.steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.eneko.PriceCalculatorApplication;
import org.eneko.cart.Cart;
import org.eneko.cart.CartService;
import org.eneko.cart.PriceService;
import org.eneko.prices.PriceRepository;
import org.eneko.prices.ProductPrice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertTrue;

/**
 * Created by eneko on 19/06/17.
 */
@SpringBootTest(classes = PriceCalculatorApplication.class)
@ContextConfiguration
public class CalculatePriceStepDefs {

    @Autowired
    private CartService cartService;

    @Autowired
    private PriceService priceService;

    private Cart cart;

    private int cartPrice;

    @Given("^a price service the following base prices \"([^\"]*)\"$")
    public void a_price_service_the_following_base_prices(String basePricesFilename) throws Throwable {
        Iterable<ProductPrice> productPrices = priceService.setupBasePrices(basePricesFilename);
        productPrices.forEach( productPrice -> assertThat(productPrice.getProductType(),notNullValue()));
    }


    @Given("^I have an cart by file \"([^\"]*)\"$")
    public void i_have_an_cart_by_file(String cartFilename) throws Throwable {
        cart = cartService.newCartFromFile(cartFilename);
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

    @Then("^Its prices is (\\d+)$")
    public void its_prices_is(int expectedPrice) throws Throwable {
        assertThat(cartPrice,equalTo(expectedPrice));
    }

}
