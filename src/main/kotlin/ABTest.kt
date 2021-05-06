import java.io.File
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

fun main() {
    val dataFile = File("src/main/resources/testdata")

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

    println("JacksonDeserializer took $elapsedTimeJackson ms")
    println("Kotlinx.Deserializer took $elapsedTimeKotlinx ms")
}
