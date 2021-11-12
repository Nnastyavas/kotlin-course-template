package lab2

import java.util.*
import kotlin.Exception
import kotlin.math.pow

private var stack: Stack<String> = Stack()
private var queue = mutableListOf<String>() //выходная полседовательность
private val operators = hashSetOf("+", "-", "*", "/", "^")

fun parseExpression(Expr: String): Double {
    val stringToChange = Expr
        .replace(" ", "")
        .replaceFirst("-(", "0-(")
    /*stringToChange.forEach {
        print(it)
    }*/
    if (stringToChange.isEmpty())
        throw Exception("Error. List is empty.")

    getPostFixEx(stringToChange)
    return calcPostFix()
}

private fun getPostFixEx(Expr: String) {
    queue.clear()
    stack.clear()
    calcBracket(Expr)
    var index = 0
    while (index < Expr.length) {
        when (Expr[index]) {
            '(' -> stack.push(Expr[index].toString())
            ')' -> if (Expr.contains('(')) pop()
            in '0'..'9' -> {
                val indexEnd = findNumber(Expr, index)
                queue.add(Expr.substring(index, indexEnd + 1))
                index = indexEnd
            }
            '+', '-' ->
                if (index == 0 || operators.contains(Expr[index - 1].toString())
                    || Expr[index - 1] == '('
                ) {
                    val indexEnd = findNumber(Expr, index + 1)
                    queue.add(Expr.substring(index, indexEnd + 1))
                    index = indexEnd
                } else {
                    if (stack.isEmpty() || stack.last() == "(") stack.push(Expr[index].toString())
                    else if (stack.last().contains('*') || stack.last().contains('/')) {
                        pop()
                        stack.push(Expr[index].toString())
                    } else {
                        queue.add(stack.last())
                        stack[stack.lastIndex] = Expr[index].toString()
                    }
                }
            '*', '/' -> {
                if (stack.isNotEmpty() && (stack.last() == "*" || stack.last() == "/")) {
                    pop()
                }
                if (Expr[index] != Expr.last() && (Expr[index + 1] == '*' || Expr[index + 1] == '/'))
                    throw Exception("Incorrect sequence of operations")
                if (Expr[index] == Expr.last() || Expr[index] == Expr.first())
                    throw Exception("Wrong the first or the last symbol")
                stack.push(Expr[index].toString())
            }
            '^' -> {
                stack.push(Expr[index].toString())
                if (Expr[index] == Expr.last() || Expr[index] == Expr.first())
                    throw Exception("Wrong the first or the last symbol")
            }
            else -> throw Exception("Wrong symbols in input")
        }
        index++
    }

    while (stack.isNotEmpty())
        queue.add(stack.pop())

    /*println("Постфиксное выражение: ")
    queue.forEach {
        print(it)
    } println()*/
}

private fun calcBracket(Expr: String) {
    var count = 0
    Expr.forEach {
        if (it == '(') count++
        if (it == ')') count--
    }
    if (count != 0)
        throw Exception("Wrong brackets")
}

private fun pop() {
    for (i in stack.lastIndex downTo 0) {
        if (stack.peek() == "(") {
            stack.pop()
            break
        }
        queue.add(stack[i])
        stack.pop()
    }
}

private fun findNumber(str: String, index: Int): Int {
    var indexEnd = index
    while (indexEnd + 1 < str.length &&
        (str[indexEnd + 1].isDigit() || str[indexEnd + 1] == '.')
    )
        indexEnd++
    return indexEnd
}

private fun calcPostFix(): Double {
    val list = mutableListOf<Double>()
    for (i in queue) {
        when {
            Regex("[\\d]").containsMatchIn(i) -> {
                list.add(i.toDouble())
            }
            i == "+" -> {
                list[list.lastIndex - 1] = list[list.lastIndex - 1] + list.last()
                list.removeAt(list.lastIndex)
            }
            i == "*" -> {
                list[list.lastIndex - 1] = list[list.lastIndex - 1] * list.last()
                list.removeAt(list.lastIndex)
            }
            i == "/" -> {
                list[list.lastIndex - 1] = list[list.lastIndex - 1] / list.last()
                list.removeAt(list.lastIndex)
            }
            i == "-" -> {
                list[list.lastIndex - 1] = list[list.lastIndex - 1] - list.last()
                list.removeAt(list.lastIndex)
            }
            i == "^" -> {
                list[list.lastIndex - 1] = (list[list.lastIndex - 1]
                    .pow(list.last()))
                list.removeAt(list.lastIndex)
            }
        }
    }
    return list.first()
}
