import java.text.DecimalFormat
import kotlin.system.measureNanoTime
import kotlin.system.measureTimeMillis
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

fun main() {
    var runResults = mutableListOf<Pair<Long, Long>>()

    for (runs in 1..20) {
        println("Test $runs")
        var jacksonTotal = 0L
        var kotlinxTotal = 0L
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
        testDataList.forEach {
            val jacksonTimeNano = measureNanoTime { it.toJson() }
            val kotlinxTimeNano = measureNanoTime { Json.encodeToString(it) }

            jacksonTotal += jacksonTimeNano
            kotlinxTotal += kotlinxTimeNano
        }
        runResults.add(jacksonTotal to kotlinxTotal)
    }

    val df = DecimalFormat("#,###,###")
    runResults.forEachIndexed { index, pair ->
        println("${index+1} | ${df.format(pair.first)} | ${df.format(pair.second)} ")
    }
}

