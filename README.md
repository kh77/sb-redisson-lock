## Spring Boot - Distributed Lock using Redisson

-----------------------------------------------------
- Java 11
- Spring Boot 2.7
- Redis (Redisson Library)
- Run Unit test and see the logs

### Curl: Post request

    curl --location --request POST 'localhost:8080/employee' \
    --header 'Content-Type: application/json' \
    --data-raw '{"email":"hello@hotmail.com","name":"hello"}'



### JMeter to test : 
    - Check `3 user request.jmx` file in the jmeter folder and import in the jmeter to check and see the logs



### Java Redis Library

    Lettuce: Lettuce is a high-performance Java Redis client that supports Redis Sentinel and Redis Cluster. It provides an asynchronous, non-blocking API that makes it well-suited for high-throughput, low-latency applications. Lettuce also supports advanced Redis features such as pipelining, transactions, and Lua scripting.


    Jedis: Jedis is a mature, feature-rich Java Redis client that provides a synchronous, blocking API. It supports Redis Sentinel and Redis Cluster, and provides features such as pipelining, transactions, and Lua scripting. Jedis is widely used and well-supported, with an active community of contributors.

    
    Redisson: Redisson is a Redis client for Java that provides a rich feature set, including distributed locks, data structures, and caching. It supports Redis Sentinel and Redis Cluster, and provides both synchronous and asynchronous APIs. Redisson is designed to be easy to use and provides a simple, fluent API.


- As for which library to choose, it depends on your specific needs and use case. If you need high performance and low latency, Lettuce might be a good choice. If you prefer a synchronous API and need more advanced features such as Lua scripting, Jedis might be a good fit. If you need a simple, easy-to-use library with support for distributed locks and other advanced Redis features, Redisson might be the best choice.

- Ultimately, you should evaluate each library based on your requirements and choose the one that best meets your needs.