import java.io.File
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

fun main() {
    val dataFile = File("src/main/resources/testdata")

    // First = jackson, second = kotlin.x
    val testResults = mutableListOf<Pair<Long, Long>>()
    var jacksonTotal = 0L
    var kotlinxTotal = 0L
    for (i in 1..100) {
        val startTimeJackson = System.currentTimeMillis()
        dataFile.forEachLine { line ->
            jackson.readValue(line, TestData::class.java)
        }
        val elapsedTimeJackson = System.currentTimeMillis() - startTimeJackson


        val startTimeKotlinx = System.currentTimeMillis()
        dataFile.forEachLine { line ->
            Json.decodeFromString<TestData>(line)
        }
        val elapsedTimeKotlinx = System.currentTimeMillis() - startTimeKotlinx

        jacksonTotal += elapsedTimeJackson
        kotlinxTotal += elapsedTimeKotlinx
        testResults.add(elapsedTimeJackson to elapsedTimeKotlinx)
    }

    testResults.forEach { result ->
        println("|${result.first} | ${result.second}")
    }

    println("Jackson took an average of ${jacksonTotal/100} ms")
    println("Kotlin.X took an average of ${kotlinxTotal/100} ms")

}
