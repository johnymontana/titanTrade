# titanTrade
This project attempts to simulate the trading market model as defined in [Kleinberg](https://www.cs.cornell.edu/home/kleinber/networks-book/) Ch 11 (Network Models of Markets with Intermediaries).
The interactions of sellers, traders, and buyers are modeled as nodes and edges in a network/graph. We attempt to simulate this model using
[Titan](http://thinkaurelius.github.io/titan/), a distributed graph database.

## Dependencies
* Using maven, Java dependencies are specified in `pom.xml`
* Cassanda - assumes Cassandra is running locally


## Data model
Simple graph based trading market consisting of sellers, traders, and buyers

![datamodel image](/img/market.png)

