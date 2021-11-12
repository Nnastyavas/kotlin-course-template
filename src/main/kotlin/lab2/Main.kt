package lab2

fun main() {
    var ex1 = "12+ 3"
    var ex2 = "-3+4^+4"
    var ex3 = "(4+3)*9/ -4"
    var ex4 = "((-4+3))*8/4"
    println ("Инфиксное выражение: $ex1.  Ответ: ${parseExpression(ex1)}")
    println ("Инфиксное выражение: $ex2.  Ответ: ${parseExpression(ex2)}")
    println ("Инфиксное выражение: $ex3.  Ответ: ${parseExpression(ex3)}")
    println ("Инфиксное выражение: $ex4.  Ответ: ${parseExpression(ex4)}")
}