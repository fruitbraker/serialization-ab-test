import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer
import java.time.LocalDate
import java.util.Date
import kotlinx.serialization.Contextual
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer
import kotlinx.serialization.UseSerializers
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

object LocalDateSerializerCustom : KSerializer<LocalDate> {
    override fun deserialize(decoder: Decoder): LocalDate {
        val payload = decoder.decodeString()
        return LocalDate.parse(payload)
    }

    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("LocalDate", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: LocalDate) = encoder.encodeString(value.toString())
}

@Serializable
data class TestData(
    val name: String,
    @Serializable(with = LocalDateSerializerCustom::class)
    val date: LocalDate,
    val integer: Int,
    val double: Double,
    val listString: List<String>,
    val listCustomData: CustomData
)

@Serializable
data class CustomData(
    val name: String,
    val integer: Int
)

