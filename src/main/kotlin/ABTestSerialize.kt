import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

fun main() {

    var jacksonTotal = 0L
    var kotlinxTotal = 0L

    var runResults = mutableListOf<Pair<Long, Long>>()

    for (runs in 1..100) {
        val testDataList = mutableListOf<TestData>()
        for (i in 1..10000) {
            testDataList.add(
                TestData(
                    name = randomString(),
                    date = randomLocalDate(),
                    integer = randomInt(),
                    double = randomDouble(),
                    listString = randomListString(),
                    listCustomData = CustomData(
                        name = randomString(),
                        integer = randomInt()
                    )
                )
            )
        }

        val startTimeJackson = System.currentTimeMillis()
        testDataList.forEach { it.toJson() }
        val elapsedTimeJackson = System.currentTimeMillis() - startTimeJackson

        val startTimeKotlinx = System.currentTimeMillis()
        testDataList.forEach { Json.encodeToString(it) }
        val elapsedTimeKotlinx = System.currentTimeMillis() - startTimeKotlinx

        jacksonTotal += elapsedTimeJackson
        kotlinxTotal += elapsedTimeKotlinx

        runResults.add(elapsedTimeJackson to elapsedTimeKotlinx)
    }

    runResults.forEach {
        println("| ${it.first} | ${it.second} ")
    }

    println("Jackson serialization took an average ${jacksonTotal/100} ms")
    println("Kotlin.X serialization took average ${kotlinxTotal/100} ms")
}

