
package org.eneko.prices.pricecalculator;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "product-type",
    "options",
    "base-price"
})
public class BasePrice {

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("product-type")
    private String productType;
    /**
     * Key-value pairs of strings. If the value is an array, the base-price applies to all the strings in that array.
     * (Required)
     * 
     */
    @JsonProperty("options")
    @JsonPropertyDescription("Key-value pairs of strings. If the value is an array, the base-price applies to all the strings in that array.")
    private BasePriceOptions options;
    /**
     * The base price for this product-type and option combination in cents.
     * (Required)
     * 
     */
    @JsonProperty("base-price")
    @JsonPropertyDescription("The base price for this product-type and option combination in cents.")
    private Integer basePrice;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("product-type")
    public String getProductType() {
        return productType;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("product-type")
    public void setProductType(String productType) {
        this.productType = productType;
    }

    /**
     * Key-value pairs of strings. If the value is an array, the base-price applies to all the strings in that array.
     * (Required)
     * 
     */
    @JsonProperty("options")
    public BasePriceOptions getOptions() {
        return options;
    }

    /**
     * Key-value pairs of strings. If the value is an array, the base-price applies to all the strings in that array.
     * (Required)
     * 
     */
    @JsonProperty("options")
    public void setOptions(BasePriceOptions options) {
        this.options = options;
    }

    /**
     * The base price for this product-type and option combination in cents.
     * (Required)
     * 
     */
    @JsonProperty("base-price")
    public Integer getBasePrice() {
        return basePrice;
    }

    /**
     * The base price for this product-type and option combination in cents.
     * (Required)
     * 
     */
    @JsonProperty("base-price")
    public void setBasePrice(Integer basePrice) {
        this.basePrice = basePrice;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
