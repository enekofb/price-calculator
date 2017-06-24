#Price Calculator

*Semantics*
Create a simple price calculator command-line program. 
In order to make this exercise programming language agnostic, the inputs and outputs are defined as JSON.

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

*Refactor pending*

- Split concerns so domain entities has data layer information and presentation. There should be
different objects.

*On install*

- Run ElasticSearch Docker container


docker run -p 9200:9200 -p 9300:9300 elasticsearch:2.4 -Des.node.name="elastic"


http://localhost:9200/product/price/_search?default_operator=AND&q=product-type:hoodie+options.size:small+options.colour:white&pretty

https://www.mkyong.com/spring-boot/spring-boot-spring-data-elasticsearch-example/

*Assumptions*

- Ignore some options for the cart type that does not applies to the price.
- First approach is to do an intersection of product by type so you just want to mach those properties
in the intersection between car-product options and price options