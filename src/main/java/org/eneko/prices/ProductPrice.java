
package org.eneko.prices;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "product-type",
    "options",
    "base-price"
})
@Document(indexName = "product",type = "price")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductPrice {

    @Id
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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
    private Map<String,Object> options;
    /**
     * The base price for this product-type and option combination in cents.
     * (Required)
     * 
     */
    @JsonProperty("base-price")
    @JsonPropertyDescription("The base price for this product-type and option combination in cents.")
    private Integer basePrice;

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
    public Map<String,Object> getOptions() {
        return options;
    }

    /**
     * Key-value pairs of strings. If the value is an array, the base-price applies to all the strings in that array.
     * (Required)
     * 
     */
    @JsonProperty("options")
    public void setOptions(Map<String,Object> options) {
        this.options = options;
    }

    /**
     * The base price for this product-type and option combination in cents.
     * (Required)
     * 
     */
    @JsonProperty("base-price")
    public Integer  getBasePrice() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductPrice that = (ProductPrice) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (productType != null ? !productType.equals(that.productType) : that.productType != null) return false;
        if (options != null ? !options.equals(that.options) : that.options != null) return false;
        return basePrice != null ? basePrice.equals(that.basePrice) : that.basePrice == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (productType != null ? productType.hashCode() : 0);
        result = 31 * result + (options != null ? options.hashCode() : 0);
        result = 31 * result + (basePrice != null ? basePrice.hashCode() : 0);
        return result;
    }
}
