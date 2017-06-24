# Price Calculator

## Description 

### Semantics

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

### Examples and test

An example of base prices is available at: /base-prices.json.

Some example carts are available at: /cart-4560.json, /cart-9363.json, /cart-9500.json, and /cart-11356.json. 

The file name includes the expected output for the above-mentioned example base prices.

### Important notes

We currently have more than 1,000 base prices. Make sure the running time of the base price lookup is constant.
Write tested, production-quality code.
Make sure a human can understand how to run your program.
You will extend this program in subsequent interview steps.

## Implementation Details

- Implemented by using BDD/TDD cycles.
- Technologies involved Java8, SpringBoot, Cucumber, ElasticSearch and Docker.
- Elasticsearch has been used so the queries needed to match several fields not Id based.

### Assumptions taken

- First approach is to do an intersection of product by type so you just want to mach those properties
in the intersection between cart-product options and price options.

### Future work and refactoring needed

- Move to command line.

- Split concerns so domain entities has data layer information and presentation. There should be
different objects by spliting in DAO that could have format annotations and Domain Entities free of them.

### Running the project

- In order to run unit-test just normal goal `mvn clean test`

- In order to run the full test suite that includes acceptance use the goal `mvn clean verify` . Notice that it starts
also elasticsearch as a docker container so it can run integration tests. It means that you need to have docker installed
in the machine running the suite.

- The following command executes ElasticSearch standaloneRun ElasticSearch Docker container
```
docker run -p 9200:9200 -p 9300:9300 elasticsearch:2.4 -Des.node.name="elastic"
```

- The following query is an example of the queries executed by the code in order to find the price
```
http://localhost:9200/product/price/_search?default_operator=AND&q=product-type:hoodie+options.size:small+options.colour:white&pretty
```