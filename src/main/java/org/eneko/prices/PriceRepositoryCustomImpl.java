package org.eneko.prices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import static org.elasticsearch.index.query.MatchQueryBuilder.Operator.AND;
import static org.elasticsearch.index.query.QueryBuilders.matchQuery;

/**
 * Created by eneko on 21/06/17.
 */
@Repository
public class PriceRepositoryCustomImpl implements PriceRepositoryCustom{

    @Autowired
    ElasticsearchTemplate esTemplate;


//    curl -XGET 'localhost:9200/product/price/_search?pretty' -H 'Content-Type: application/json' -d'
//    {
//        "query": {
//            "match": {
//                    "product-type": "hoodie",
//                    "options.size": "small"
//            }
//        }
//    }'

    @Override
    public ProductPrice findProductByProductTypeAndProperties(String productType, Map<String, Object> additionalProperties) {
        final NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        queryBuilder.withQuery(matchQuery("product-type",productType).operator(AND));
        additionalProperties.entrySet().stream().forEach(entry -> queryBuilder.withQuery(matchQuery(entry.getKey(),entry.getValue())));
        List<ProductPrice> productPrices = esTemplate.queryForList(queryBuilder.build(), ProductPrice.class);
        return productPrices.get(0);
    }

}
