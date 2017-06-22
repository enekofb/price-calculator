package org.eneko.prices;

import org.eneko.cart.CartProduct;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;

/**
 * Created by eneko on 21/06/17.
 */
//@Component
public interface PriceRepository extends ElasticsearchRepository<ProductPrice, String>,PriceRepositoryCustom {

    public Collection<ProductPrice> findProductByProductType(String productType);

}
