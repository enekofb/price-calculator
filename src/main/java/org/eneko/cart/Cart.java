
package org.eneko.cart;

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
    "artist-markup",
    "quantity"
})
public class Cart {

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("product-type")
    private String productType;
    /**
     * Key-value pairs of strings.
     * (Required)
     * 
     */
    @JsonProperty("options")
    @JsonPropertyDescription("Key-value pairs of strings.")
    private CartOptions options;
    /**
     * The artist markup in percent, for example 20 represents a 20% markup.
     * (Required)
     * 
     */
    @JsonProperty("artist-markup")
    @JsonPropertyDescription("The artist markup in percent, for example 20 represents a 20% markup.")
    private Integer artistMarkup;
    /**
     * The quantity of this item.
     * (Required)
     * 
     */
    @JsonProperty("quantity")
    @JsonPropertyDescription("The quantity of this item.")
    private Integer quantity;
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
     * Key-value pairs of strings.
     * (Required)
     * 
     */
    @JsonProperty("options")
    public CartOptions getOptions() {
        return options;
    }

    /**
     * Key-value pairs of strings.
     * (Required)
     * 
     */
    @JsonProperty("options")
    public void setOptions(CartOptions options) {
        this.options = options;
    }

    /**
     * The artist markup in percent, for example 20 represents a 20% markup.
     * (Required)
     * 
     */
    @JsonProperty("artist-markup")
    public Integer getArtistMarkup() {
        return artistMarkup;
    }

    /**
     * The artist markup in percent, for example 20 represents a 20% markup.
     * (Required)
     * 
     */
    @JsonProperty("artist-markup")
    public void setArtistMarkup(Integer artistMarkup) {
        this.artistMarkup = artistMarkup;
    }

    /**
     * The quantity of this item.
     * (Required)
     * 
     */
    @JsonProperty("quantity")
    public Integer getQuantity() {
        return quantity;
    }

    /**
     * The quantity of this item.
     * (Required)
     * 
     */
    @JsonProperty("quantity")
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
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
