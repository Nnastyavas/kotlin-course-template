package lab7

import lab3.*
import lab6.ShapeCollector

fun main() {

    val inText = "src\\main\\kotlin\\lab7\\in.json"
    val outText = "src\\main\\kotlin\\lab7\\out.json"

    val shapes = ShapeCollector<Shape>()
    val factory = ShapeFactorImpl()
    val square = Square(2.0)

    shapes.addAll(Serialization.decode<List<Shape>>(FileIO.readFromFile(inText)!!))
    shapes.add(factory.createRandomCircle())
    shapes.add(factory.createTriangle(3.0, 4.0, 5.0))
    shapes.add(factory.createRandomShape())
    shapes.add(Serialization.decode<Square>(Serialization.encode(square)))
    FileIO.writeToFile(Serialization.encode(shapes.getAll()), outText)
}