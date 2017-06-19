Feature: Calculate price for a cart
  As a ecommerce customer
  I want to calculate the prices of my cart
  So I can determine its cost

  Background:
    Given exists a price calculator with prices "base-price-schema.json" and cart "cart-schema.json"

  Scenario: Can calculate the price of an empty cart
    Given I have an empty cart
    When I calculate its price
    Then Its prices is 0
