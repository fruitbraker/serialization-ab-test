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

File | Jackson (ms) | KotlinX (ms)
----|---------------|-----------
testdata|1774 ms|545 ms

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

Base on the only result run, clearly KotlinX wins, but a more comprehensive test is WIP.
