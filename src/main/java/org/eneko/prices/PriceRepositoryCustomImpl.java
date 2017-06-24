package org.eneko.prices;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.elasticsearch.index.query.MatchQueryBuilder.Operator.AND;
import static org.elasticsearch.index.query.QueryBuilders.boolQuery;
import static org.elasticsearch.index.query.QueryBuilders.matchQuery;

/**
 * Created by eneko on 21/06/17.
 */
public class PriceRepositoryCustomImpl implements PriceRepositoryCustom{

    @Autowired
    ElasticsearchTemplate esTemplate;

//    eneko@eneko-XPS-15-9550:~$ curl -XGET 'localhost:9200/product/price/_search?pretty' -H 'Content-Type: application/json' -d'
//    {
//        "query": {
//        "bool": {
//            "must": [
//            { "match": { "product-type":{"query":  "hoodie","operator":"and" }}},
//            { "match": { "options.size":{"query":"small","operator":"and"}}}, { "match": { "options.colour":{"query":"white","operator":"and"}}}
//      ]
//        }
//    }
//    }
//'

    @Override
    //TODO: refactor me, I want to be simpler
    public ProductPrice findByProductTypeAndOptions(String productType, Map<String, Object> options, Set<String> validOptions) {
        final BoolQueryBuilder boolQueryBuilder = boolQuery().must(matchQuery("product-type", productType).operator(AND));
        Stream<Map.Entry<String, Object>> optionsToInclude = options.entrySet().stream().
                filter(entry -> validOptions.contains(entry.getKey()));
        optionsToInclude.forEach(entry -> boolQueryBuilder.must(matchQuery("options." + entry.getKey(), entry.getValue()).operator(AND)));
        List<ProductPrice> productPrices = esTemplate.queryForList(new NativeSearchQueryBuilder()
                .withQuery(boolQueryBuilder)
                .build(), ProductPrice.class);
        if(productPrices.isEmpty()){
            throwExpectioin(productType,options);
        }
        return productPrices.get(0);
    }

    private void throwExpectioin(String productType, Map<String, Object> options) {
        String optionsAsString = options.entrySet().stream().map(entry -> String.format("%s:%s", entry.getKey(), entry.getValue())).collect(Collectors.joining(","));
        String illegalProductMsg = String.format("Not found price for product with type '%s' and options '%s'", productType, optionsAsString);
        throw new RuntimeException(illegalProductMsg);
    }

}
