package lab4

class Matrix(var matrix: Array<Array<Double>>) {

    private val columns: Int = matrix[0].size //колонки
    private val rows: Int = matrix.size //строки

    // Просмотр размерностей матрицы
    private fun getColumns(): Int = columns
    private fun getRows(): Int = rows

    fun size(): String {
        return ("[${getRows()}, ${getColumns()}]")
    }

    //Инициализация матрицы
    init {
        if (matrix.isEmpty() || matrix[0].isEmpty())
            throw IllegalArgumentException("Matrix is empty")
        matrix.forEach {
            if (it.size != getColumns())
                throw IllegalArgumentException("Size of the rows must be the same")
        }
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
        if (getRows() == other.getRows() && getColumns() == other.getColumns()) {
            val otherMatrix: Array<Array<Double>> = Array(getRows()) { Array(getColumns()) { 0.0 } }
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
        if (getRows() == other.getRows() && getColumns() == other.getColumns()) {
            val otherMatrix: Array<Array<Double>> = Array(getRows()) { Array(getColumns()) { 0.0 } }
            for (i in 0 until getRows()) {
                for (j in 0 until getColumns()) {
                    otherMatrix[i][j] += matrix[i][j] - other.matrix[i][j]
                }
            }
            return Matrix(otherMatrix)
        }
        throw IllegalArgumentException("Wrong size of matrix")
    }

    operator fun times(other: Matrix): Matrix {
        if (getColumns() == other.getRows()) {
            val otherMatrix: Array<Array<Double>> = Array(getRows()) { Array(other.getColumns()) { 0.0 } }
            for (i in 0 until getRows()) {
                for (j in 0 until other.getColumns()) {
                    for (k: Int in 0 until getColumns()) {
                        otherMatrix[i][j] += matrix[i][k] * other.matrix[k][j]
                    }
                }
            }
            return Matrix(otherMatrix)
        } else throw IllegalArgumentException("Columns are not equal to rows")
    }

    // Операции += -= *= (модификация операций слева)
    operator fun plusAssign(other: Matrix) {
        if (getColumns() == other.getColumns() && getRows() == other.getRows()) {
            for (i in 0 until getRows()) {
                for (j in 0 until getColumns()) {
                    matrix[i][j] += other[i, j]
                }
            }
        } else throw IllegalArgumentException("Wrong size of matrix")
    }

    operator fun minusAssign(other: Matrix) {
        if (getColumns() == other.getColumns() && getRows() == other.getRows()) {
            for (i in 0 until getRows()) {
                for (j in 0 until getColumns()) {
                    matrix[i][j] -= other[i, j]
                }
            }
        } else throw IllegalArgumentException("Wrong size of matrix")
    }

    operator fun timesAssign(other: Matrix) {
        if (getColumns() == other.getRows()) {
            val tempMatrix: Array<Array<Double>> = Array(getRows()) { Array(other.getColumns()) { 0.0 } }
            for (i in 0 until getRows()) {
                for (j in 0 until other.getColumns()) {
                    for (k: Int in 0 until getColumns()) {
                        tempMatrix[i][j] += matrix[i][k] * other.matrix[k][j]
                    }
                }
            }
            matrix = tempMatrix
        } else throw IllegalArgumentException("Columns are not equal to rows")
    }

    //Умножение и деление на скаляр (создание новой матрицы)
    operator fun times(scalar: Double): Matrix {
        val otherMatrix: Array<Array<Double>> = Array(getRows()) { Array(getColumns()) { 0.0 } }
        for (i in 0 until getRows()) {
            for (j in 0 until getColumns())
                otherMatrix[i][j] += (matrix[i][j] * scalar)
        }
        return Matrix(otherMatrix)
    }

    operator fun div(scalar: Double): Matrix {
        val otherMatrix: Array<Array<Double>> = Array(getRows()) { Array(getColumns()) { 0.0 } }
        if (scalar == 0.0)
            throw ArithmeticException("Division by zero is not possible")
        for (i in 0 until getRows()) {
            for (j in 0 until getColumns()) {
                otherMatrix[i][j] += (matrix[i][j] / scalar)
            }
        }
        return Matrix(otherMatrix)
    }

    //Умножение и деление на скаляр (Модификация матрицы слева)
    operator fun timesAssign(scalar: Double) {
        for (i in 0 until getRows()) {
            for (j in 0 until getColumns())
                matrix[i][j] = (matrix[i][j] * scalar)
        }
    }

    operator fun divAssign(scalar: Double) {
        if (scalar == 0.0)
            throw ArithmeticException("Division by zero is not possible")
        for (i in 0 until getRows()) {
            for (j in 0 until getColumns()) {
                matrix[i][j] = (matrix[i][j] / scalar)
            }
        }
    }

    // Операторы унарного минуса и унарного плюса
    operator fun unaryMinus(): Matrix {
        for (i in 0 until getRows()) {
            for (j in 0 until getColumns()) {
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
        if (temp.getColumns() != getColumns() || temp.getRows() != getRows())
            return false
        for (i in 0 until getRows())
            for (j in 0 until getColumns())
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
