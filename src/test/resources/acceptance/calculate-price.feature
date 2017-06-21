Feature: Calculate price for a cart
  As a ecommerce customer
  I want to calculate the prices of my cart
  So I can determine its cost

  Background:
    Given a price service with schema "base-price-schema.json" and base prices "example-base-prices.json"
    And a cart service with schema "cart-schema.json"

  Scenario: Can calculate the price of an empty cart
    Given I have an empty cart
    When I calculate its price
    Then Its prices is 0

  Scenario: Can calculate the price of an empty cart
    Given I have an cart by file "cart-4560.json"
    When I calculate its price
    Then Its prices is 0

