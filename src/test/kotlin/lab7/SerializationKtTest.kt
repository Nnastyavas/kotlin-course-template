package lab7

import lab3.*
import lab6.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class SerializationKtTest {

    @Test
    fun test_encode() {
        val square = Square(1.0)
        val circle = Circle(5.0)
        val triangle = Triangle(3.0, 4.0, 5.0)
        val rectangle = Rectangle(1.0, 7.0)
        assertEquals(
            """
            {
                "sideA": 1.0
            }
        """.trimIndent(), Serialization.encode(square)
        )

        assertEquals(
            """
            {
                "radius": 5.0
            }
        """.trimIndent(), Serialization.encode(circle)
        )

        assertEquals(
            """
            {
                "sideA": 3.0,
                "sideB": 4.0,
                "sideC": 5.0
            }
        """.trimIndent(), Serialization.encode(triangle)
        )
        assertEquals(
            """
            {
                "sideA": 1.0,
                "sideB": 7.0
            }
        """.trimIndent(), Serialization.encode(rectangle)
        )
    }

    @Test
    fun test_encode_listShape() {
        val square = Square(1.0)
        val circle = Circle(5.0)
        val shapes = ShapeCollector<Shape>()
        shapes.addAll(listOf(square, circle))
        assertEquals(
            """
            [
                {
                    "type": "lab3.Square",
                    "sideA": 1.0
                },
                {
                    "type": "lab3.Circle",
                    "radius": 5.0
                }
            ]
        """.trimIndent(), Serialization.encode(shapes.getAll())
        )
    }

    @Test
    fun test_decode() {
        val square = Square(1.0)
        val circle = Circle(5.0)
        assertEquals("Square (sideA: 1.0)", Serialization.decode<Square>(Serialization.encode(square)).toString())
        assertEquals("Circle (radius: 5.0)", Serialization.decode<Circle>(Serialization.encode(circle)).toString())
    }

    @Test
    fun test_decode_shapes() {
        val square = Square(1.0)
        val circle = Circle(5.0)
        val shapes = ShapeCollector<Shape>()
        shapes.addAll(listOf(square, circle))
        assertEquals(
            "[Square (sideA: 1.0), Circle (radius: 5.0)]",
            Serialization.decode<List<Shape>>(Serialization.encode(shapes.getAll())).toString()
        )
    }
}