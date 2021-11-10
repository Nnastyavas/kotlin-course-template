import java.lang.IllegalArgumentException
import kotlin.math.sqrt
import kotlin.random.Random

interface Shape {
    fun calcArea(): Double
    fun calcPerimeter(): Double
}

class Circle (val radius: Double): Shape {
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
}
class Square (val sideA: Double): Shape {
    init {
        if (sideA <= 0)
            throw IllegalArgumentException ("Incorrect side of the square")
    }
    override fun calcArea(): Double {
        return sideA * sideA
    }

    override fun calcPerimeter(): Double {
        return 4 * sideA
    }
}
class Rectangle (val sideA: Double, val sideB: Double): Shape {
    init {
        if (sideA <= 0 || sideB <= 0)
            throw IllegalArgumentException ("Incorrect side of the rectangle")
    }

    override fun calcArea(): Double {
        return sideA * sideB
    }

    override fun calcPerimeter(): Double {
        return 2 * (sideA + sideB)
    }
}
class Triangle (val sideA: Double, val sideB: Double, val sideC: Double): Shape {
    init {
        if (sideA <=0 || sideB <= 0 || sideC <= 0
            || sideA + sideB <= sideC
            || sideA + sideC <= sideB
            || sideB + sideC <= sideA
        )
            throw IllegalArgumentException ("Incorrect side of the triangle")
    }
    override fun calcArea(): Double {
        val p: Double = calcPerimeter() / 2
        return sqrt(p * (p - sideA) * (p - sideB) * (p - sideC))
    }

    override fun calcPerimeter(): Double {
        return sideA + sideB + sideC
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
    //private val max = Double.MAX_VALUE
    private val max = 9999.9999
    private val min = 0.0001

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
        println("It's Circle")
      return Circle(Random.nextDouble(min, max))
    }

    override fun createRandomSquare(): Square {
        println("It's Square")
        return Square(Random.nextDouble(min, max))
    }

    override fun createRandomRectangle(): Rectangle {
        println("It's Rectangle")
        return Rectangle(Random.nextDouble(min, max), Random.nextDouble(min, max))
    }

    override fun createRandomTriangle(): Triangle {
        println("It's Triangle")
        var sideA: Double
        var sideB: Double
        var sideC: Double
        do {
             sideA = Random.nextDouble(min, max)
             sideB = Random.nextDouble(min, max)
             sideC = Random.nextDouble(min, max)
        }
        while (sideA + sideB <= sideC
            || sideA + sideC <= sideB
            || sideB + sideC <= sideA
        )
        return Triangle(sideA, sideB, sideC)
    }

    override fun createRandomShape(): Shape {
        return when ((1..4).random()) {
            1 -> createRandomCircle()
            2 -> createRandomSquare()
            3 -> createRandomRectangle()
            4 -> createRandomTriangle()
            else -> throw IllegalArgumentException("Random argument error")
        }

    }
}

