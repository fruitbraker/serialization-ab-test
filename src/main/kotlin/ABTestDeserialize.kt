import java.io.File
import kotlin.system.measureTimeMillis
import kotlin.time.Duration
import kotlin.time.ExperimentalTime
import kotlin.time.measureTime
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

@OptIn(ExperimentalTime::class)
fun main() {
    val dataFile = File("src/main/resources/testdata")

    // First = jackson, second = kotlin.x
    val testResults = mutableListOf<Pair<Long, Long>>()

    for (i in 1..100) {
        var jacksonTotal = 0L
        var kotlinxTotal = 0L
        dataFile.forEachLine { line ->
            val jacksonTimeMillis = measureTimeMillis {
                jackson.readValue(line, TestData::class.java)
            }
            jacksonTotal += jacksonTimeMillis

            val kotlinxTimeMillis = measureTimeMillis {
                Json.decodeFromString<TestData>(line)
            }
            kotlinxTotal += kotlinxTimeMillis
        }
        testResults.add(jacksonTotal to kotlinxTotal)
    }

    testResults.forEach {
        println("jackson: ${it.first} ms   kotlinx: ${it.second} ms")
    }
}
