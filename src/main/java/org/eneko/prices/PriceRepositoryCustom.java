package org.eneko.prices;

import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;

/**
 * Created by eneko on 21/06/17.
 */
//@Component
public interface PriceRepositoryCustom {

    public ProductPrice findProductByProductTypeAndProperties(String productType, Map<String, Object> additionalProperties);
}
