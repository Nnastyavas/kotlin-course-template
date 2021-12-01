package lab6

import Circle
import Shape
import ShapeFactorImpl

fun main() {

    val shapeCollector = ShapeCollector<Shape>()
    val factory = ShapeFactorImpl()
    shapeCollector.add(factory.createRectangle(1.0, 2.0))
    shapeCollector.add(factory.createTriangle(3.0, 4.0, 5.0))
    shapeCollector.addAll(
        listOf(
            factory.createSquare(2.0),
            factory.createRectangle(4.0, 6.0),
            factory.createCircle(3.0)
        )
    )

    println("All shape:" + shapeCollector.getAll())

    println("\nSort by areaAsc:")
    shapeCollector.getAllSorted(ShapeComparators.areaAsc).forEach {
        println(it.calcArea())
    }

    println("\nSort by areaDesc:")
    shapeCollector.getAllSorted(ShapeComparators.areaDesc).forEach {
        println(it.calcArea())
    }

    println("\nSort by PerimeterAsc:")
    shapeCollector.getAllSorted(ShapeComparators.perimeterAsc).forEach {
        println(it.calcPerimeter())
    }

    println("\nSort by PerimeterDesc:")
    shapeCollector.getAllSorted(ShapeComparators.perimeterDesc).forEach {
        println(it.calcPerimeter())
    }

    val shapeCircle = ShapeCollector<Circle>()
    shapeCircle.add(factory.createCircle(1.0))
    shapeCircle.add(factory.createCircle(10.0))
    shapeCircle.addAll(
        listOf(
            factory.createCircle(8.0),
            factory.createCircle(2.0),
            factory.createCircle(3.0)
        )
    )

    println("\nAll circle:" + shapeCollector.getAll())

    println("\nSort by areaAsc:")
    shapeCircle.getAllSorted(ShapeComparators.areaAsc).forEach {
        println(it.calcArea())
    }

    println("\nSort by areaDesc:")
    shapeCircle.getAllSorted(ShapeComparators.areaDesc).forEach {
        println(it.calcArea())
    }

    println("\nSort by PerimeterAsc:")
    shapeCircle.getAllSorted(ShapeComparators.perimeterAsc).forEach {
        println(it.calcPerimeter())
    }

    println("\nSort by PerimeterDesc:")
    shapeCircle.getAllSorted(ShapeComparators.perimeterDesc).forEach {
        println(it.calcPerimeter())
    }

    println("\nSort by RadiusAsc:")
    shapeCircle.getAllSorted(ShapeComparators.radiusAsc).forEach {
        println(it.radius)
    }

    println("\nSort by RadiusDesc:")
    shapeCircle.getAllSorted(ShapeComparators.radiusDesc).forEach {
        println(it.radius)
    }
}
