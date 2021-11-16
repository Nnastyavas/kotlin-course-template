package lab3

import Shape
import ShapeFactorImpl

fun main () {
    val factory= ShapeFactorImpl()
    val listOfShapes: List<Shape> = listOf(
        factory.createCircle(3.0), //P = 18,84955592153876, S = 28,27433388230814
        factory.createSquare(7.0), //P = 28, S = 49
        factory.createRectangle(2.0,6.0), //P = 16, S = 12
        factory.createTriangle(3.0,4.0,5.0), //P = 12, S = 6
    )
    val sumArea = listOfShapes.sumOf { it.calcArea() }
    val sumPerimeter = listOfShapes.sumOf { it.calcPerimeter() }
    val minArea = listOfShapes.minOfOrNull { it.calcArea() }
    val maxArea = listOfShapes.maxOfOrNull { it.calcArea() }
    val minPerimeter = listOfShapes.minOfOrNull { it.calcPerimeter() }
    val maxPerimeter = listOfShapes.maxOfOrNull { it.calcPerimeter() }
    println("""
        Sum of area: $sumArea
        Sum of Perimeter: $sumPerimeter
        Min and max value of Area: $minArea and $maxArea
        Min and max value of Perimeter: $minPerimeter and $maxPerimeter
    """.trimIndent())
    println("_____________________________________________________________________")
    val listOfRandomShapes: List <Shape> = listOf(
        factory.createRandomCircle(),
        factory.createRandomSquare(),
        factory.createRandomRectangle(),
        factory.createRandomTriangle(),
    )
    val sumRandomArea = listOfRandomShapes.sumOf { it.calcArea() }
    val sumRandomPerimeter = listOfRandomShapes.sumOf { it.calcPerimeter() }
    val minRandomArea = listOfRandomShapes.minOfOrNull { it.calcArea() }
    val maxRandomArea = listOfRandomShapes.maxOfOrNull { it.calcArea() }
    val minRandomPerimeter = listOfRandomShapes.minOfOrNull { it.calcPerimeter() }
    val maxRandomPerimeter = listOfRandomShapes.maxOfOrNull { it.calcPerimeter() }
    println("""
        Sum of area: $sumRandomArea
        Sum of Perimeter: $sumRandomPerimeter
        Min and max value of Area: $minRandomArea and $maxRandomArea
        Min and max value of Perimeter: $minRandomPerimeter and $maxRandomPerimeter
    """.trimIndent())
    println("_____________________________________________________________________")


    val listOfRandomShapes2: List <Shape> = listOf(
        factory.createRandomShape(),
        factory.createRandomShape(),
        factory.createRandomShape()
    )
    val sumRandom2Area = listOfRandomShapes2.sumOf { it.calcArea() }
    val sumRandom2Perimeter = listOfRandomShapes2.sumOf { it.calcPerimeter() }
    val minRandom2Area = listOfRandomShapes2.minOfOrNull { it.calcArea() }
    val maxRandom2Area = listOfRandomShapes2.maxOfOrNull { it.calcArea() }
    val minRandom2Perimeter = listOfRandomShapes2.minOfOrNull { it.calcPerimeter() }
    val maxRandom2Perimeter = listOfRandomShapes2.maxOfOrNull { it.calcPerimeter() }
    println(""" Random shapes.
        Sum of area: $sumRandom2Area
        Sum of Perimeter: $sumRandom2Perimeter
        Min and max value of Area: $minRandom2Area and $maxRandom2Area
        Min and max value of Perimeter: $minRandom2Perimeter and $maxRandom2Perimeter
    """.trimIndent())

}