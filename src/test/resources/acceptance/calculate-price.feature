Feature: Calculate price for a cart
  As a ecommerce customer
  I want to calculate the prices of my cart
  So I can determine its cost

  Background:
    Given a price service the following base prices "base-prices.json"

  Scenario: Can calculate the price of an empty cart
    Given I have an empty cart
    When I calculate its price
    Then Its prices is "0"

  Scenario Outline: Can calculate the price of an empty cart
    Given I have a cart given by "<cartFile>"
    When I calculate its price
    Then Its prices is "<cartPrice>"

    Examples:
      | cartFile       | cartPrice |
      | cart-4560.json | 4560     |
      | cart-9363.json | 9363     |
      | cart-9500.json | 9500     |
      | cart-11356.json | 11356     |
