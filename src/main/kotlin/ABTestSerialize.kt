import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

fun main() {
    val testDataList = mutableListOf<TestData>()
    for (i in 1..1000) {
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

    println("Jackson serialization took $elapsedTimeJackson ms")
    println("Kotlin.X serialization took $elapsedTimeKotlinx ms")
}

