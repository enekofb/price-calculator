package org.eneko.prices;

import java.util.Map;
import java.util.Set;

/**
 * Created by eneko on 21/06/17.
 */
public interface PriceRepositoryCustom {

    public ProductPrice findByProductTypeAndOptions(String productType, Map<String, Object> options, Set<String> validOptions);
}
