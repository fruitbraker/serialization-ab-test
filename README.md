# Serialization A/B test

There are many serialization/deserialization libraries out there. A popular one is [Jackson](https://github.com/FasterXML/jackson).
Jackson is a already a great library, has been around for many years and trusted by many developers. 

JetBrains has also developed a [Serialization/Deserialization](https://github.com/Kotlin/kotlinx.serialization) library.

## What are the differences?
_WIP_
The only immediate difference is that Kotlin.X requires additional configuration for serializing/deserializing non-primitive types,
such as `LocalDate`. Jackson automatically does that for you (at least for LocalDate) if you have the correct modules. 

## Environment

* Kotlin version `1.5.0`
* Kotlin.X version `1.2.0`
* Jackson (kotlin module with LocalDate support) version `2.12.0`
* Intel i5-3570k (i know it's old), 16GB RAM, Windows 10
* IntelliJ v2021.1.1

## Results

### Deserialization
File | Jackson (ms) | KotlinX (ms)
----|---------------|-----------
testdata|1774 ms|545 ms

### Serialization

Randomly generating 1000 test objects

Jackson serialization took 751 ms

Kotlin.X serialization took 101 ms

## Conclusion

Right now, there's only a limited set of data...only 10,000 randomly generated json for a custom dataclass with properties that I think
are commonly used: 

* String
* Integer
* Double
* List
* Some sort of reference to another serializable object

```
data class TestData(
    val name: String,
    val date: LocalDate,
    val integer: Int,
    val double: Double,
    val listString: List<String>,
    val listCustomData: CustomData
)

data class CustomData(
    val name: String,
    val integer: Int
)
```

## Some weird findings (May 8, 2021)

Normally, there would be mutiple runs. I thought that simply putting the test I'm running into a for loop and call it a day.
But, when examining the results, the first run shows that kotlinx runs much faster than jackson. But subsequent runs show that 
both of these libraries have roughly the same performance.

Example for Serializing. I ran 20 runs (put in a loop) where each run randomly generates 10,000 TestData for jackson and kotlinx to serialize
into JSON.

File | Jackson (ns) | KotlinX (ns)
----|---------------|-----------
1 | 950,090,500 | 406,257,900
2 | 46,035,100 | 114,045,300
3 | 22,914,700 | 31,445,600
4 | 22,183,500 | 26,067,800
5 | 23,595,800 | 26,343,200
6 | 34,468,300 | 42,038,100
7 | 22,612,000 | 25,393,100
8 | 19,374,400 | 21,405,400
9 | 18,686,900 | 21,084,000
10 | 17,948,900 | 21,285,100
11 | 19,010,000 | 21,613,000
12 | 19,303,500 | 21,014,400
13 | 18,756,100 | 22,361,000
14 | 20,194,300 | 22,235,600
15 | 19,667,400 | 21,699,800
16 | 22,510,800 | 35,108,100
17 | 21,386,700 | 24,212,300
18 | 19,120,300 | 21,229,400
19 | 19,321,000 | 21,349,500
20 | 18,381,500 | 21,376,900

Observe that the first run clearly shows that kotlinx is the winner. But subsequent runs show that both libraries are close.
There are a couple thoughts on why this is happening.
