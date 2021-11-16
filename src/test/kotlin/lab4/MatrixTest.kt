package lab4

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

internal class MatrixTest {

    @Test
    fun exception_EmptyMatrix() {
        try {
            Matrix(arrayOf(arrayOf()))
        } catch (e: Exception) {
            assertEquals("Matrix is empty", e.message)
        }
    }

    @Test
    fun exception_SizeRows() {
        try {
            Matrix(
                arrayOf(
                    arrayOf(1.0, 2.0),
                    arrayOf(4.0),
                    arrayOf(7.0, 8.0)
                )
            )
        } catch (e: Exception) {
            assertEquals("Size of the rows must be the same", e.message)
        }
    }

    @Test
    fun exception_PlusOrMinus() {
        try {
            val matrix = Matrix(
                arrayOf(
                    arrayOf(1.0, 2.0),
                    arrayOf(4.0, 1.0),
                    arrayOf(7.0, 8.0)
                )
            )
            val matrix2 = Matrix(
                arrayOf(
                    arrayOf(1.0, 2.0),
                    arrayOf(4.0, 1.0)
                )
            )
            matrix + matrix2
        } catch (e: Exception) {
            assertEquals("Wrong size of matrix", e.message)
        }
    }

    @Test
    fun exception_Times() {
        try {
            val matrix = Matrix(
                arrayOf(
                    arrayOf(1.0, 2.0, 9.0),
                    arrayOf(4.0, 7.0, 8.0)
                )
            )
            matrix * matrix
        } catch (e: Exception) {
            assertEquals("Columns are not equal to rows", e.message)
        }
    }

    @Test
    fun exception_MinusAssign() {
        try {
            val matrix = Matrix(
                arrayOf(
                    arrayOf(1.0, 2.0),
                    arrayOf(4.0, 1.0),
                    arrayOf(7.0, 8.0)
                )
            )
            val matrix2 = Matrix(
                arrayOf(
                    arrayOf(1.0, 2.0),
                    arrayOf(4.0, 1.0)
                )
            )
            matrix -= matrix2
        } catch (e: Exception) {
            assertEquals("Wrong size of matrix", e.message)
        }
    }

    @Test
    fun exception_TimesAssign() {
        try {
            val matrix = Matrix(
                arrayOf(
                    arrayOf(1.0, 2.0, 9.0),
                    arrayOf(4.0, 7.0, 8.0)
                )
            )
            matrix *= matrix
        } catch (e: Exception) {
            assertEquals("Columns are not equal to rows", e.message)
        }
    }

    @Test
    fun exception_DivScalar() {
        try {
            val matrix = Matrix(
                arrayOf(
                    arrayOf(1.0, 2.0),
                    arrayOf(4.0, 1.0),
                    arrayOf(7.0, 8.0)
                )
            )
            val scalar = 0.0
            matrix / scalar
        } catch (e: Exception) {
            assertEquals("Division by zero is not possible", e.message)
        }
    }

    @Test
    fun exception_DivAssignScalar() {
        try {
            val matrix = Matrix(
                arrayOf(
                    arrayOf(1.0, 2.0),
                    arrayOf(4.0, 1.0),
                    arrayOf(7.0, 8.0)
                )
            )
            val scalar = 0.0
            matrix /= scalar
        } catch (e: Exception) {
            assertEquals("Division by zero is not possible", e.message)
        }
    }

    @Test
    fun size() {
        val matrix = Matrix(
            arrayOf(
                arrayOf(1.0, 2.0),
                arrayOf(4.0, 1.0),
                arrayOf(7.0, 8.0)
            )
        )
        assertEquals("[3, 2]", matrix.size())
    }

    @Test
    fun set() {
        val matrix = Matrix(
            arrayOf(
                arrayOf(1.0, 2.0),
                arrayOf(4.0, 1.0),
                arrayOf(7.0, 8.0)
            )
        )
        matrix[0, 0] = 4.0
        assertEquals(4.0, matrix[0, 0])
    }

    @Test
    fun get() {
        val matrix = Matrix(
            arrayOf(
                arrayOf(1.0, 2.0),
                arrayOf(4.0, 1.0),
                arrayOf(7.0, 8.0)
            )
        )
        assertEquals(8.0, matrix[2, 1])
    }

    @Test
    fun plus() {
        val matrix = Matrix(
            arrayOf(
                arrayOf(1.0, 2.0),
                arrayOf(4.0, 1.0),
                arrayOf(7.0, 8.0)
            )
        )
        val matrix2 = Matrix(
            arrayOf(
                arrayOf(2.0, 2.0),
                arrayOf(4.0, 1.0),
                arrayOf(1.0, 5.0)
            )
        )
        val expected = "[3.0][4.0]" + System.lineSeparator() +
                "[8.0][2.0]" + System.lineSeparator() +
                "[8.0][13.0]" + System.lineSeparator()
        assertEquals(expected, (matrix + matrix2).toString())
        assertEquals(1.0, matrix[0, 0])
        assertNotEquals(3.0, matrix[0, 0])
    }

    @Test
    fun plusAssign() {
        val matrix = Matrix(
            arrayOf(
                arrayOf(1.0, 2.0),
                arrayOf(4.0, 1.0),
                arrayOf(7.0, 8.0)
            )
        )
        val matrix2 = Matrix(
            arrayOf(
                arrayOf(2.0, 2.0),
                arrayOf(4.0, 1.0),
                arrayOf(1.0, 5.0)
            )
        )
        matrix += matrix2
        val expected = "[3.0][4.0]" + System.lineSeparator() +
                "[8.0][2.0]" + System.lineSeparator() +
                "[8.0][13.0]" + System.lineSeparator()
        assertEquals(expected, matrix.toString())
        assertEquals(3.0, matrix[0, 0])
        assertNotEquals(1.0, matrix[0, 0])
    }

    @Test
    fun minus() {
        val matrix = Matrix(
            arrayOf(
                arrayOf(1.0, -2.0),
                arrayOf(-4.0, 1.0),
                arrayOf(7.0, -8.0)
            )
        )
        val matrix2 = Matrix(
            arrayOf(
                arrayOf(2.0, 2.0),
                arrayOf(4.0, 1.0),
                arrayOf(1.0, 5.0)
            )
        )
        val expected = "[-1.0][-4.0]" + System.lineSeparator() +
                "[-8.0][0.0]" + System.lineSeparator() +
                "[6.0][-13.0]" + System.lineSeparator()
        assertEquals(expected, (matrix - matrix2).toString())
        assertEquals(1.0, matrix[0, 0])
        assertNotEquals(-1.0, matrix[0, 0])
    }

    @Test
    fun minusAssign() {
        val matrix = Matrix(
            arrayOf(
                arrayOf(1.0, -2.0),
                arrayOf(-4.0, 1.0),
                arrayOf(7.0, -8.0)
            )
        )
        val matrix2 = Matrix(
            arrayOf(
                arrayOf(2.0, 2.0),
                arrayOf(4.0, 1.0),
                arrayOf(1.0, 5.0)
            )
        )
        matrix -= matrix2
        val expected = "[-1.0][-4.0]" + System.lineSeparator() +
                "[-8.0][0.0]" + System.lineSeparator() +
                "[6.0][-13.0]" + System.lineSeparator()
        assertEquals(expected, matrix.toString())
        assertEquals(-1.0, matrix[0, 0])
        assertNotEquals(1.0, matrix[0, 0])
    }

    @Test
    fun times() {
        val matrix = Matrix(
            arrayOf(
                arrayOf(1.0, 2.0),
                arrayOf(4.0, 5.0),
                arrayOf(7.0, 8.0)
            )
        )
        val matrix2 = Matrix(
            arrayOf
                (
                arrayOf(1.0, 2.0, 3.0),
                arrayOf(4.0, 5.0, 6.0)
            )
        )
        val expected = "[9.0][12.0][15.0]" + System.lineSeparator() +
                "[24.0][33.0][42.0]" + System.lineSeparator() +
                "[39.0][54.0][69.0]" + System.lineSeparator()
        assertEquals(expected, (matrix * matrix2).toString())
        assertEquals(1.0, matrix[0, 0])
        assertNotEquals(9.0, matrix[0, 0])
    }

    @Test
    fun timesAssign() {
        val matrix = Matrix(
            arrayOf(
                arrayOf(1.0, 2.0),
                arrayOf(4.0, 5.0),
                arrayOf(7.0, 8.0)
            )
        )
        val matrix2 = Matrix(
            arrayOf
                (
                arrayOf(1.0, 2.0, 3.0),
                arrayOf(4.0, 5.0, 6.0)
            )
        )
        matrix *= matrix2
        val expected = "[9.0][12.0][15.0]" + System.lineSeparator() +
                "[24.0][33.0][42.0]" + System.lineSeparator() +
                "[39.0][54.0][69.0]" + System.lineSeparator()
        assertEquals(expected, matrix.toString())
        assertEquals(9.0, matrix[0, 0])
        assertNotEquals(1.0, matrix[0, 0])
    }

    @Test
    fun timesScalar() {
        val matrix = Matrix(
            arrayOf(
                arrayOf(3.0, 5.0),
                arrayOf(4.0, 2.0)
            )
        )
        val scalar = 4.0
        val expected = "[12.0][20.0]" + System.lineSeparator() +
                "[16.0][8.0]" + System.lineSeparator()
        assertEquals(expected, (matrix * scalar).toString())
        assertEquals(3.0, matrix[0, 0])
        assertNotEquals(12.0, matrix[0, 0])
    }

    @Test
    fun timesAssignScalar() {
        val matrix = Matrix(
            arrayOf(
                arrayOf(3.0, 5.0),
                arrayOf(4.0, 2.0)
            )
        )
        val scalar = 4.0
        matrix *= scalar
        val expected = "[12.0][20.0]" + System.lineSeparator() +
                "[16.0][8.0]" + System.lineSeparator()
        assertEquals(expected, matrix.toString())
        assertEquals(12.0, matrix[0, 0])
        assertNotEquals(3.0, matrix[0, 0])
    }

    @Test
    fun divScalar() {
        val matrix = Matrix(
            arrayOf(
                arrayOf(3.0, 5.0),
                arrayOf(4.0, 2.0)
            )
        )
        val scalar = 2.0
        val expected = "[1.5][2.5]" + System.lineSeparator() +
                "[2.0][1.0]" + System.lineSeparator()
        assertEquals(expected, (matrix / scalar).toString())
        assertEquals(3.0, matrix[0, 0])
        assertNotEquals(1.5, matrix[0, 0])
    }

    @Test
    fun divAssignScalar() {
        val matrix = Matrix(
            arrayOf(
                arrayOf(3.0, 5.0),
                arrayOf(4.0, 2.0)
            )
        )
        val scalar = 2.0
        matrix /= scalar
        val expected = "[1.5][2.5]" + System.lineSeparator() +
                "[2.0][1.0]" + System.lineSeparator()
        assertEquals(expected, matrix.toString())
        assertEquals(1.5, matrix[0, 0])
        assertNotEquals(3, matrix[0, 0])
    }

    @Test
    operator fun unaryMinus() {
        val matrix = Matrix(
            arrayOf(
                arrayOf(3.0, -5.0),
                arrayOf(-4.0, 2.0)
            )
        )
        val expected = "[-3.0][5.0]" + System.lineSeparator() +
                "[4.0][-2.0]" + System.lineSeparator()
        assertEquals(expected, (-matrix).toString())
        assertEquals(-3.0, matrix[0, 0])
        assertNotEquals(3.0, matrix[0, 0])
    }

    @Test
    operator fun unaryPlus() {
        val matrix = Matrix(
            arrayOf(
                arrayOf(3.0, -5.0),
                arrayOf(-4.0, 2.0)
            )
        )
        val expected = "[3.0][-5.0]" + System.lineSeparator() +
                "[-4.0][2.0]" + System.lineSeparator()
        assertEquals(expected, (+matrix).toString())
        assertEquals(3.0, matrix[0, 0])
    }

    @Test
    fun testEquals() {
        val matrix = Matrix(
            arrayOf(
                arrayOf(3.0, -5.0),
                arrayOf(-4.0, 2.0)
            )
        )
        val matrix2 = Matrix(
            arrayOf(
                arrayOf(3.0, -5.0),
                arrayOf(-4.0, 2.0)
            )
        )
        assertEquals(true, matrix == matrix2)
    }

    @Test
    fun testNotEquals() {
        val matrix = Matrix(
            arrayOf(
                arrayOf(1.0, -5.0),
                arrayOf(-4.0, 2.0)
            )
        )
        val matrix2 = Matrix(
            arrayOf(
                arrayOf(3.0, -5.0),
                arrayOf(-4.0, 2.0)
            )
        )
        assertEquals(false, matrix == matrix2)
    }
}