package org.eneko.prices;

import org.eneko.PriceCalculatorApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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