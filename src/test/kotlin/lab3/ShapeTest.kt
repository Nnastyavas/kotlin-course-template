package lab3

import ShapeFactorImpl
import ShapeFactory
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import kotlin.IllegalArgumentException
import kotlin.math.PI

internal class ShapeTest {
    companion object {
        val factory: ShapeFactory = ShapeFactorImpl()
    }

    @Test
    fun circleException() {
        try {
            factory.createCircle(12.0)
        } catch (e: IllegalArgumentException) {
            fail()
        }
    }

    @Test
    fun squareException() {
        try {
            factory.createSquare(2.0)
        } catch (e: IllegalArgumentException) {
            fail()
        }
    }

    @Test
    fun rectangleException() {
        try {
            factory.createRectangle(2.0, 4.0)
        } catch (e: IllegalArgumentException) {
            fail()
        }
    }

    @Test
    fun triangleException_negative() {
        try {
            factory.createTriangle(3.0, 4.0, 5.0)
        } catch (e: IllegalArgumentException) {
            fail()
        }
    }

    @Test
    fun triangleException_sizeOfSide() {
        try {
            factory.createTriangle(2.0, 4.0, 5.0)
        } catch (e: IllegalArgumentException) {
            fail()
        }
    }

    @Test
    fun circleArea() {
        val circle = factory.createCircle(2.0)
        assertEquals(PI * 2.0 * 2.0, circle.calcArea())
        assertNotEquals(12.0, circle.calcArea())
    }

    @Test
    fun circlePerimeter() {
        val circle = factory.createCircle(3.0)
        assertEquals(2 * PI * 3, circle.calcPerimeter())
        assertNotEquals(12.0, circle.calcPerimeter())
    }

    @Test
    fun squareArea() {
        val square = factory.createSquare(6.0)
        assertEquals(36.0, square.calcArea())
        assertNotEquals(24.0, square.calcArea())
    }

    @Test
    fun squarePerimeter() {
        val square = factory.createSquare(6.0)
        assertEquals(24.0, square.calcPerimeter())
        assertNotEquals(36.0, square.calcPerimeter())
    }

    @Test
    fun rectangleArea() {
        val rectangle = factory.createRectangle(6.0, 5.0)
        assertEquals(30.0, rectangle.calcArea())
        assertNotEquals(22.0, rectangle.calcArea())
    }

    @Test
    fun rectanglePerimeter() {
        val rectangle = factory.createRectangle(6.0, 5.0)
        assertEquals(22.0, rectangle.calcPerimeter())
        assertNotEquals(30.0, rectangle.calcPerimeter())
    }

    @Test
    fun triangleArea() {
        val triangle = factory.createTriangle(3.0, 4.0, 5.0)
        assertEquals(6.0, triangle.calcArea())
        assertNotEquals(12.0, triangle.calcArea())
    }

    @Test
    fun trianglePerimeter() {
        val triangle = factory.createTriangle(3.0, 4.0, 5.0)
        assertEquals(12.0, triangle.calcPerimeter())
        assertNotEquals(6.0, triangle.calcPerimeter())
    }
}