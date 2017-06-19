package org.eneko.prices.pricecalculator;

import lombok.*;

/**
 * Created by eneko on 19/06/17.
 */
@Builder
@NoArgsConstructor

@AllArgsConstructor
@Getter
@Setter
public class PriceCalculator {

    private String basePriceSchemaFilename;

    private String cartSchemaFilename;
}
