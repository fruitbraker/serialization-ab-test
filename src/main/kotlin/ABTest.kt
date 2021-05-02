import java.io.File
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

fun main() {
    val dataFile = File("src/main/resources/testdata")

    val startTimeJackson = System.currentTimeMillis()
    dataFile.forEachLine { line ->
        jackson.readValue(line, TestData::class.java)
    }
    val endTimeJackson = System.currentTimeMillis()
    val elapsedTimeJackson = endTimeJackson - startTimeJackson

    println("JacksonDeserializer took $elapsedTimeJackson ms")
}
