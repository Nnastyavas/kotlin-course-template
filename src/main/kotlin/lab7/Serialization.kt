package lab7

import kotlinx.serialization.*
import lab3.*
import kotlinx.serialization.modules.*
import kotlinx.serialization.json.Json


object Serialization {
    val json = Json {
        prettyPrint = true

        serializersModule = SerializersModule {
            polymorphic(Shape::class) {
                subclass(Square::class)
                subclass(Rectangle::class)
                subclass(Triangle::class)
                subclass(Circle::class)
            }
        }
    }

    inline fun <reified T> encode(value: T): String {
        return json.encodeToString(value)
    }

    inline fun <reified T> decode(string: String): T {
        return json.decodeFromString(string)
    }
}
