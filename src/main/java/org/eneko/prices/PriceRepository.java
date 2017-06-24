package org.eneko.prices;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * Created by eneko on 21/06/17.
 */
@Repository
public interface PriceRepository extends ElasticsearchRepository<ProductPrice, String>,PriceRepositoryCustom{
//    public ProductPrice findByProductTypeAndOptions(String productType, Map<String, Object> options);
}
