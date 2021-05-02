import java.io.File


fun main() {
    val startTime = System.currentTimeMillis()

    File("src/main/resources/1.json").readLines()

    val endTime = System.currentTimeMillis()
    val elapsedTime = endTime - startTime
    println("JacksonDeserializer took $elapsedTime ms")
}
