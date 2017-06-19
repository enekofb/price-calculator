#Price Calculator

*Semantics*
Create a simple price calculator command-line program. In order to make this exercise programming language agnostic, the inputs and outputs are defined as JSON.

*Input*
Your command-line program should take two arguments:
a file representing a cart, and
a file representing a list of base prices.

*Output*
Your program is expected to output the total price in cents followed by a newline character.


*Resources*

The *cart schema* is available at:  /home/eneko/projects/eneko/tests/price-calculator/src/main/resources/cart-schema-.json. 
The *base price schema* is available at: /home/eneko/projects/eneko/tests/price-calculator/src/main/resources/base-price-schema.json

*You can assume that the options keys for a product-type are constant*
For example, if the first record with the product-type 'hoodie' in the list of base prices only consists of the options keys 'colour' and 'size', all records with the product-type 'hoodie' will only have the options keys 'colour' and 'size'.
The *price calculation* for one item is as follows: (base_price + round(base_price * artist_markup)) * quantity

*Examples and test*

An example of base prices is available at: /base-prices.json.

Some example carts are available at: /cart-4560.json, /cart-9363.json, /cart-9500.json, and /cart-11356.json. 

The file name includes the expected output for the above-mentioned example base prices.

*Important notes:*

We currently have more than 1,000 base prices. Make sure the running time of the base price lookup is constant.
Write tested, production-quality code.
Make sure a human can understand how to run your program.
You will extend this program in subsequent interview steps.