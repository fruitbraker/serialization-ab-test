import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

fun main() {
//    for (i: Int in 1..10000) {
//        val testData = TestData(
//            name = randomString(),
//            date = randomLocalDate(),
//            integer = randomInt(),
//            double = randomDouble(),
//            listString = randomListString(),
//            listCustomData = CustomData(
//                name = randomString(),
//                integer = randomInt()
//            )
//        )
//
//        File("src/main/resources/testdata").appendText("${testData.toJson()}\n")
//    }


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

    val jsonTestData = Json.encodeToString(testData)
    println(jsonTestData)

    val fromJsonTestData = Json.decodeFromString<TestData>(jsonTestData)
    println(fromJsonTestData)
}
