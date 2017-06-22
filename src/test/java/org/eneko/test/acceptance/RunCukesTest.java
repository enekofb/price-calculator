package org.eneko.test.acceptance;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.eneko.PriceCalculatorApplication;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@CucumberOptions(plugin = { "pretty", "html:target/cucumber-html-report" }, features = {
        "src/test/resources/acceptance"})
@RunWith(Cucumber.class)
public class RunCukesTest {

}