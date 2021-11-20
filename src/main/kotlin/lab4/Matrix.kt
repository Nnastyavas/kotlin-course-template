package lab4

class Matrix(private val _matrix: Array<Array<Double>>) {

    private var matrix: Array<Array<Double>> = emptyArray()
    val columns: Int = _matrix[0].size
    val rows: Int = _matrix.size

    //Инициализация матрицы
    init {
        if (_matrix.isEmpty() || _matrix[0].isEmpty())
            throw IllegalArgumentException("Matrix is empty")
        _matrix.forEach {
            if (it.size != columns)
                throw IllegalArgumentException("Size of the rows must be the same")
        }
        matrix = Array(rows) { Array(columns) { 0.0 } }
        for (i in 0 until rows)
            for (j in 0 until columns)
                matrix[i][j] = _matrix[i][j]
    }

    //Просмотр значения элемента в позиции (i,j) и изменения этого элемента
    operator fun set(i: Int, j: Int, value: Double) {
        matrix[i][j] = value
    }

    operator fun get(i: Int, j: Int): Double {
        return matrix[i][j]
    }

    // Операции + - * (создание новой матрицы)
    operator fun plus(other: Matrix): Matrix {
        if (rows == other.rows && columns == other.columns) {
            val otherMatrix: Array<Array<Double>> = Array(rows) { Array(columns) { 0.0 } }
            for (i in 0 until rows) {
                for (j in 0 until columns) {
                    otherMatrix[i][j] += matrix[i][j] + other.matrix[i][j]
                }
            }
            return Matrix(otherMatrix)
        }
        throw IllegalArgumentException("Wrong size of matrix")
    }

    operator fun minus(other: Matrix): Matrix {
        if (rows == other.rows && columns == other.columns) {
            val otherMatrix: Array<Array<Double>> = Array(rows) { Array(columns) { 0.0 } }
            for (i in 0 until rows) {
                for (j in 0 until columns) {
                    otherMatrix[i][j] += matrix[i][j] - other.matrix[i][j]
                }
            }
            return Matrix(otherMatrix)
        }
        throw IllegalArgumentException("Wrong size of matrix")
    }

    operator fun times(other: Matrix): Matrix {
        if (columns == other.rows) {
            val otherMatrix: Array<Array<Double>> = Array(rows) { Array(other.columns) { 0.0 } }
            for (i in 0 until rows) {
                for (j in 0 until other.columns) {
                    for (k: Int in 0 until columns) {
                        otherMatrix[i][j] += matrix[i][k] * other.matrix[k][j]
                    }
                }
            }
            return Matrix(otherMatrix)
        } else throw IllegalArgumentException("Columns are not equal to rows")
    }

    // Операции += -= *= (модификация операций слева)
    operator fun plusAssign(other: Matrix) {
        if (columns == other.columns && rows == other.rows) {
            for (i in 0 until rows) {
                for (j in 0 until columns) {
                    matrix[i][j] += other[i, j]
                }
            }
        } else throw IllegalArgumentException("Wrong size of matrix")
    }

    operator fun minusAssign(other: Matrix) {
        if (columns == other.columns && rows == other.rows) {
            for (i in 0 until rows) {
                for (j in 0 until columns) {
                    matrix[i][j] -= other[i, j]
                }
            }
        } else throw IllegalArgumentException("Wrong size of matrix")
    }

    operator fun timesAssign(other: Matrix) {
        if (columns == other.rows) {
            val tempMatrix: Array<Array<Double>> = Array(rows) { Array(other.columns) { 0.0 } }
            for (i in 0 until rows) {
                for (j in 0 until other.columns) {
                    for (k: Int in 0 until columns) {
                        tempMatrix[i][j] += matrix[i][k] * other.matrix[k][j]
                    }
                }
            }
            matrix = tempMatrix
        } else throw IllegalArgumentException("Columns are not equal to rows")
    }

    //Умножение и деление на скаляр (создание новой матрицы)
    operator fun times(scalar: Double): Matrix {
        val otherMatrix: Array<Array<Double>> = Array(rows) { Array(columns) { 0.0 } }
        for (i in 0 until rows) {
            for (j in 0 until columns)
                otherMatrix[i][j] += (matrix[i][j] * scalar)
        }
        return Matrix(otherMatrix)
    }

    operator fun div(scalar: Double): Matrix {
        val otherMatrix: Array<Array<Double>> = Array(rows) { Array(columns) { 0.0 } }
        if (scalar == 0.0)
            throw ArithmeticException("Division by zero is not possible")
        for (i in 0 until rows) {
            for (j in 0 until columns) {
                otherMatrix[i][j] += (matrix[i][j] / scalar)
            }
        }
        return Matrix(otherMatrix)
    }

    //Умножение и деление на скаляр (Модификация матрицы слева)
    operator fun timesAssign(scalar: Double) {
        for (i in 0 until rows) {
            for (j in 0 until columns)
                matrix[i][j] = (matrix[i][j] * scalar)
        }
    }

    operator fun divAssign(scalar: Double) {
        if (scalar == 0.0)
            throw ArithmeticException("Division by zero is not possible")
        for (i in 0 until rows) {
            for (j in 0 until columns) {
                matrix[i][j] = (matrix[i][j] / scalar)
            }
        }
    }

    // Операторы унарного минуса и унарного плюса
    operator fun unaryMinus(): Matrix {
        for (i in 0 until rows) {
            for (j in 0 until columns) {
                if (matrix[i][j] != 0.0)
                    matrix[i][j] = -matrix[i][j]
            }
        }
        return Matrix(matrix)
    }

    operator fun unaryPlus(): Matrix {
        return this
    }

    //Сравнение двух матриц ==
    override operator fun equals(other: Any?): Boolean {
        val temp: Matrix = other as Matrix
        if (temp.columns != columns || temp.rows != rows)
            return false
        for (i in 0 until rows)
            for (j in 0 until columns)
                if (matrix[i][j] != temp[i, j])
                    return false
        return true
    }

    //Вывод состояния матрицы в строку toString
    override fun toString(): String {
        var str = ""
        for (element in matrix) {
            for (j in 0 until matrix[0].size) {
                str += "["
                str += element[j].toString()
                str += "]"
            }
            str += System.lineSeparator()
        }
        return str
    }

    override fun hashCode(): Int {    //сгенериррован средой разработки
        var result = matrix.contentDeepHashCode()
        result = 31 * result + columns
        result = 31 * result + rows
        return result
    }
}
