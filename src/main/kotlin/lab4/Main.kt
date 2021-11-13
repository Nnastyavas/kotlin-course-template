package lab4

fun main () {
    val matrix1 = Matrix(arrayOf(
        arrayOf(1.0,2.0),
        arrayOf(4.0,5.0),
        arrayOf(7.0,8.0)))
    val matrix2 = Matrix(arrayOf(
        arrayOf(1.0,2.0),
        arrayOf(4.0,5.0),
        arrayOf(7.0,8.0)))
    println("Первая матрица:\n$matrix1")
    println("Вторая матрица:\n$matrix2")
    println("Сложение + (создание новой матрицы) :\n${(matrix1 + matrix2)}")
    println("Проверка индекса [0, 0] первой матрицы: " + matrix1 [0,0])
    matrix1 += matrix2
    println("\nСложение += (модификация матрицы):\n${(matrix1)}")
    println("Проверка индекса [0, 0] первой матрицы: " + matrix1 [0,0] + ". Он изменился")
    println("__________________________________________________________________________________")

    val matrix3 = Matrix(arrayOf(
        arrayOf(3.0,2.0),
        arrayOf(4.0,-5.0),
        arrayOf(-7.0,1.0)))
    val matrix4 = Matrix(arrayOf(
        arrayOf(5.0,-2.0),
        arrayOf(4.0,5.0),
        arrayOf(7.0,8.0)))
    println("Первая матрица:\n$matrix3")
    println("Вторая матрица:\n$matrix4")
    println("Вычитание - (создание новой матрицы):\n${(matrix3-matrix4)}")
    println("Проверка индекса [0, 0] первой матрицы: " + matrix3 [0,0])
    matrix3 -= matrix4
    println("Вычитание -= (модификация матрицы):\n${(matrix3)}")
    println("Проверка индекса [0, 0] первой матрицы: " + matrix3 [0,0] + ". Он изменился")
    println("__________________________________________________________________________________")

    val matrix5 = Matrix(arrayOf(
        arrayOf(1.0,2.0),
        arrayOf(4.0,5.0),
        arrayOf(7.0,8.0)))
    val matrix6 = Matrix(arrayOf
        (arrayOf(1.0,2.0,3.0),
        arrayOf(4.0,5.0,6.0)))
    println("Первая матрица:\n$matrix5")
    println("Размерность первой матрицы:\n${matrix5.size()}")
    println("\nВторая матрица:\n$matrix6")
    println("Размерность второй матрицы:\n${matrix6.size()}")
    println("\nУмножение двух матриц * (Создание новой матрицы): \n ${matrix5 * matrix6}")
    println("Проверка индекса [0, 0] первой матрицы: " + matrix5 [0,0])
    matrix5 *= matrix6
    println("\nУмножение двух матриц */(Модификация матрицы): \n $matrix5")
    println("Проверка индекса [0, 0] первой матрицы: " + matrix5 [0,0] + ". Он изменился")
    println("__________________________________________________________________________________")

    val scalar = 3.0
    val matrix7 = Matrix(arrayOf(
        arrayOf(5.0, -7.0),
        arrayOf(12.0, 15.0),
        arrayOf(-36.0, 0.0)))
    println("Матрица:\n$matrix7")
    println("Скаляр = $scalar")
    println("\nУмножение на скаляр * (создание новой матрицы):\n${(matrix7 * scalar)}")
    println("Проверка индекса [0, 0] первой матрицы: " + matrix7 [0,0])
    matrix7 *= scalar
    println("\nУмножение на скаляр */ (модификация матрицы):\n${(matrix7)}")
    println("Проверка индекса [0, 0] первой матрицы: " + matrix7 [0,0] + ". Он изменился")
    println("__________________________________________________________________________________")

    val matrix8 = Matrix(arrayOf(
        arrayOf(9.0, -7.0),
        arrayOf(12.0, 15.0),
        arrayOf(-36.0, 0.0)))
    println("Матрица:\n$matrix8")
    println("Скаляр = $scalar")
    println("\nДеление на скаляр / (создание новой матрицы):\n${(matrix8 / scalar)}")
    println("Проверка индекса [0, 0] первой матрицы: " + matrix8 [0,0])
    matrix8 /= scalar
    println("\nДеление на скаляр /= (модификация матрицы):\n${(matrix8)}")
    println("Проверка индекса [0, 0] первой матрицы: " + matrix8 [0,0] + ". Он изменился")
    println("__________________________________________________________________________________")

    val matrix9 = Matrix(arrayOf(
        arrayOf(1.0,-2.0),
        arrayOf(-4.0, 0.0),
        arrayOf(7.0,-8.0)))
    println("Матрица:\n$matrix9")
    println("Операция унарный плюс: \n${+matrix9}")
    println("Операция унарный минус: \n${-matrix9}")
    println("__________________________________________________________________________________")

    val other1 = Matrix(arrayOf(arrayOf(1.0,2.0), arrayOf(4.0,5.0)))
    val other2 = Matrix(arrayOf(arrayOf(1.0,2.0), arrayOf(4.0,5.0)))
    val other3 = Matrix(arrayOf(arrayOf(2.0,1.0), arrayOf(5.0,4.0)))
    println("Матрица1:\n$other1")
    println("Матрица2:\n$other2")
    println("Матрица3:\n$other3")
    println("Сравнение матрицы1 и матрицы2: ${other1 == other2})")
    println("Сравнение матрицы2 и матрицы3: ${other2 == other3})")

    println("Изменение по индексу. Матрица1[0,1] = ${other1[0,1]})")
    other1[0,1] = 3.0
    println("После изменения. Матрица1[0,1] = ${other1[0,1]})")

}