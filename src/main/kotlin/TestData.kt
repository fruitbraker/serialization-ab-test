import java.time.LocalDate

data class TestData(
    val name: String,
    val date: LocalDate,
    val integer: Int,
    val double: Double,
    val listString: List<String>,
    val listCustomData: CustomData
)

data class CustomData(
    val name: String,
    val integer: Int
)
