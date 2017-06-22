package org.eneko.prices;

import org.eneko.PriceCalculatorApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

/**
 * Created by eneko on 22/06/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PriceCalculatorApplication.class)
public class PriceRepositoryTest {

    @Autowired
    PriceRepository priceRepository;

    @Test
    public void canSaveProduct(){
        ProductPrice productPrice = ProductPrice.builder().productType("type").build();
        ProductPrice productPriceSaved = priceRepository.save(productPrice);
        assertThat(productPrice.getProductType(),equalTo(productPriceSaved.getProductType()));
    }

    @Test
    public void canFindProductByProductTypeAndProperties(){
        Map<String, Object> additionalPropertiesMap= new HashMap<String, Object>();
        additionalPropertiesMap.put("colour","white");
        additionalPropertiesMap.put("size","small");
        ProductPrice productPrice = ProductPrice.builder()
                .productType("type")
                .additionalProperties(additionalPropertiesMap)
                .build();
        ProductPrice productPriceSaved = priceRepository.save(productPrice);
        ProductPrice foundProductPrice = priceRepository.findProductByProductTypeAndProperties("type", additionalPropertiesMap);
        assertThat(foundProductPrice,equalTo(productPriceSaved));
    }


}