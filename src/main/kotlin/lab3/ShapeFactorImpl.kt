package lab3

import java.lang.IllegalArgumentException
import kotlin.math.abs
import kotlin.math.sqrt
import kotlin.random.Random
import kotlinx.serialization.Serializable

interface Shape {
    fun calcArea(): Double
    fun calcPerimeter(): Double
}

@Serializable
class Circle(val radius: Double) : Shape {
    init {
        if (radius <= 0)
            throw IllegalArgumentException("Incorrect radius")
    }

    override fun calcArea(): Double {
        return Math.PI * radius * radius
    }

    override fun calcPerimeter(): Double {
        return 2 * Math.PI * radius
    }

    override fun toString(): String {
        return "Circle (radius: $radius)"
    }
}

@Serializable
class Square(val sideA: Double) : Shape {
    init {
        if (sideA <= 0)
            throw IllegalArgumentException("Incorrect side of the square")
    }

    override fun calcArea(): Double {
        return sideA * sideA
    }

    override fun calcPerimeter(): Double {
        return 4 * sideA
    }

    override fun toString(): String {
        return "Square (sideA: $sideA)"
    }
}

@Serializable
class Rectangle(val sideA: Double, val sideB: Double) : Shape {
    init {
        if (sideA <= 0 || sideB <= 0)
            throw IllegalArgumentException("Incorrect side of the rectangle")
    }

    override fun calcArea(): Double {
        return sideA * sideB
    }

    override fun calcPerimeter(): Double {
        return 2 * (sideA + sideB)
    }

    override fun toString(): String {
        return "Rectangle (sideA: $sideA, sideB: $sideB)"
    }
}

@Serializable
class Triangle(val sideA: Double, val sideB: Double, val sideC: Double) : Shape {
    init {
        if (sideA <= 0 || sideB <= 0 || sideC <= 0
            || sideA + sideB <= sideC
            || sideA + sideC <= sideB
            || sideB + sideC <= sideA
        )
            throw IllegalArgumentException("Incorrect side of the triangle")
    }

    override fun calcArea(): Double {
        val p: Double = calcPerimeter() / 2
        return sqrt(p * (p - sideA) * (p - sideB) * (p - sideC))
    }

    override fun calcPerimeter(): Double {
        return sideA + sideB + sideC
    }

    override fun toString(): String {
        return "Triangle (sideA: $sideA, sideB: $sideB, sideC: $sideC)"
    }
}

interface ShapeFactory {
    fun createCircle(radius: Double): Circle
    fun createSquare(sideA: Double): Square
    fun createRectangle(sideA: Double, sideB: Double): Rectangle
    fun createTriangle(sideA: Double, sideB: Double, sideC: Double): Triangle

    fun createRandomCircle(): Circle
    fun createRandomSquare(): Square
    fun createRandomRectangle(): Rectangle
    fun createRandomTriangle(): Triangle

    fun createRandomShape(): Shape
}

class ShapeFactorImpl : ShapeFactory {
    override fun createCircle(radius: Double): Circle {
        return Circle(radius)
    }

    override fun createSquare(sideA: Double): Square {
        return Square(sideA)
    }

    override fun createRectangle(sideA: Double, sideB: Double): Rectangle {
        return Rectangle(sideA, sideB)
    }

    override fun createTriangle(sideA: Double, sideB: Double, sideC: Double): Triangle {
        return Triangle(sideA, sideB, sideC)
    }

    override fun createRandomCircle(): Circle {
        return Circle(doubleRandom())
    }

    override fun createRandomSquare(): Square {
        return Square(doubleRandom())
    }

    override fun createRandomRectangle(): Rectangle {
        return Rectangle(doubleRandom(), doubleRandom())
    }

    override fun createRandomTriangle(): Triangle {
        val sideA = doubleRandom()
        val sideB = doubleRandom()
        val sideC = doubleRandom(abs(sideA - sideB), (sideA + sideB))
        return Triangle(sideA, sideB, sideC)
    }

    override fun createRandomShape(): Shape {
        return when ((1..4).random()) {
            1 -> createRandomCircle()
            2 -> createRandomSquare()
            3 -> createRandomRectangle()
            4 -> createRandomTriangle()
            else -> throw IllegalStateException("Random argument error")
        }
    }

    private fun doubleRandom(min: Double = 0.0001, max: Double = 100000.0): Double {
        return Random.nextDouble(min, max)
    }
}

