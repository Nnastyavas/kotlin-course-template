package lab6

import Circle
import Shape

object ShapeComparators {
    val areaAsc = compareBy<Shape> { it.calcArea() }
    val areaDesc = compareByDescending<Shape> { it.calcArea() }
    val perimeterAsc = compareBy<Shape> { it.calcPerimeter() }
    val perimeterDesc = compareByDescending<Shape> { it.calcPerimeter() }
    val radiusAsc = compareBy<Circle> { it.radius }
    val radiusDesc = compareByDescending<Circle> { it.radius }
}

class ShapeCollector<T : Shape> {
    private val allShapes = mutableListOf<T>()

    fun add(new: T) {
        allShapes.add(new)
    }

    fun addAll(new: Collection<T>) {
        allShapes.addAll(new)
    }

    fun getAll(): List<T> {
        return allShapes
    }

    fun getAllSorted(comparator: Comparator<in T>): List<T> {
        return allShapes.sortedWith(comparator)
    }

}


