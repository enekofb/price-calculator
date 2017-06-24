package org.eneko;

import org.eneko.cart.utils.JsonValidator;
import org.eneko.prices.PriceRepositoryCustom;
import org.eneko.prices.PriceRepositoryCustomImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "org.eneko")
@Configuration
@EnableElasticsearchRepositories(basePackages = "org.eneko.prices",repositoryImplementationPostfix = "CustomImpl")
public class PriceCalculatorApplication {

    public static void main(String[] args) {
        SpringApplication.run(PriceCalculatorApplication.class, args);
    }

    @Bean(name = "priceValidator")
    @Qualifier("priceValidator")
    public JsonValidator priceValidator(){
        return new JsonValidator("/base-price-schema.json");
    }

    @Bean(name = "cartValidator")
    @Qualifier("cartValidator")
    public JsonValidator cartValidator(){
        return new JsonValidator("/cart-schema.json");
    }

    @Bean
    public PriceRepositoryCustom priceRepositoryCustom(){
        return new PriceRepositoryCustomImpl();
    }


}
