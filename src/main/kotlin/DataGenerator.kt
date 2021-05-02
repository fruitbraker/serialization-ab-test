import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import java.io.File
import java.math.BigDecimal
import java.math.RoundingMode
import java.time.LocalDate
import kotlin.random.Random

val jackson: ObjectMapper = jacksonObjectMapper()
    .registerModule(Jdk8Module())
    .registerModule(JavaTimeModule())
    .registerModule(KotlinModule())
    .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)

fun Any.toJson(): String = jackson.writeValueAsString(this)

private val random = Random(System.currentTimeMillis())

// For random string
private val charPool: List<Char> = ('a'..'z') + ('A'..'Z')

fun randomString(length: Int = 5) =
    (1..length)
        .map { charPool.random() }
        .joinToString("")

fun randomInt(min: Int = Int.MIN_VALUE, max: Int = Int.MAX_VALUE): Int = random.nextInt(min, max)

fun randomListString(): List<String> {
    val list = mutableListOf<String>()
    for (i in 1..randomInt(10, 1000)) {
        list.add(randomString())
    }
    return list
}

fun randomDouble(): Double = random.nextDouble(0.000, 99999999.9999)

fun randomLong(min: Long = Long.MIN_VALUE, max: Long = Long.MAX_VALUE) = random.nextLong(min, max)

fun randomLocalDate(): LocalDate = LocalDate.now().plusDays(randomLong(0, 1000))

fun main() {
    for (i: Int in 1..10000) {
        val testData = TestData(
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

        File("src/main/resources/testdata").appendText("${testData.toJson()}\n")
    }
}
