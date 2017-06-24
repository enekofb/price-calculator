package org.eneko.test.unit.prices;

import lombok.NoArgsConstructor;
import org.eneko.PriceCalculatorApplication;
import org.eneko.prices.PriceRepository;
import org.eneko.prices.ProductPrice;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.transaction.AfterTransaction;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.elasticsearch.node.NodeBuilder.nodeBuilder;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Created by eneko on 22/06/17.
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = PriceRepositoryTestConfig.class)
public class PriceRepositoryTest {

    @Autowired
    PriceRepository priceRepository;

    @After
    public void cleanup(){
        priceRepository.deleteAll();
    }

    @Test
    public void canSaveProduct(){
        ProductPrice productPrice = ProductPrice.builder().productType("type").build();
        ProductPrice productPriceSaved = priceRepository.save(productPrice);
        assertThat(productPrice.getProductType(),equalTo(productPriceSaved.getProductType()));
    }

    @Test
    public void canFindProductByProductTypeAndAllValidOptions(){
        Map<String, Object> options= new HashMap<String, Object>();
        options.put("colour","white");
        options.put("size","small");
        Set<String> validOptions= new HashSet<>();
        validOptions.add("size");
        validOptions.add("colour");
        ProductPrice productPrice = ProductPrice.builder()
                .productType("type")
                .options(options)
                .build();
        ProductPrice productPriceSaved = priceRepository.save(productPrice);
        ProductPrice foundProductPrice = priceRepository.findByProductTypeAndOptions("type",options,validOptions);
        assertTrue(foundProductPrice.equals(productPriceSaved));
    }


}