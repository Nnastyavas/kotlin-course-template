package lab2

import java.util.*
import kotlin.math.pow

private val operators = hashSetOf("+", "-", "*", "/", "^")

fun parseExpression(Expr: String): Double {
    val stack: Stack<String> = Stack()
    val queue = mutableListOf<String>()
    val stringToChange = Expr
        .replace(" ", "")
        .replaceFirst("-(", "0-(")

    if (stringToChange.isEmpty())
        throw IllegalArgumentException("Error. Expression can not be empty.")

    getPostFixEx(stringToChange, stack, queue)
    return calcPostFix(queue)
}

private fun getPostFixEx(Expr: String, stack: Stack<String>, queue: MutableList<String>) {
    calcBracket(Expr)
    var index = 0
    while (index < Expr.length) {
        when (Expr[index]) {
            '(' -> stack.push(Expr[index].toString())
            ')' -> if (Expr.contains('(')) pop(stack, queue)
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
                        pop(stack, queue)
                        stack.push(Expr[index].toString())
                    } else {
                        queue.add(stack.last())
                        stack[stack.lastIndex] = Expr[index].toString()
                    }
                }
            '*', '/' -> {
                if (stack.isNotEmpty() && (stack.last() == "*" || stack.last() == "/")) {
                    pop(stack, queue)
                }
                if (Expr[index] != Expr.last() && (Expr[index + 1] == '*' || Expr[index + 1] == '/'))
                    throw IllegalArgumentException("Incorrect sequence of operations")
                if (Expr[index] == Expr.last() || Expr[index] == Expr.first())
                    throw IllegalArgumentException("Wrong the first or the last symbol")
                stack.push(Expr[index].toString())
            }
            '^' -> {
                stack.push(Expr[index].toString())
                if (Expr[index] == Expr.last() || Expr[index] == Expr.first())
                    throw IllegalArgumentException("Wrong the first or the last symbol")
            }
            else -> throw IllegalArgumentException("Wrong symbols in input")
        }
        index++
    }

    while (stack.isNotEmpty())
        queue.add(stack.pop())
}

private fun calcBracket(Expr: String) {
    var count = 0
    Expr.forEach {
        if (it == '(') count++
        if (it == ')') count--
    }
    if (count != 0)
        throw IllegalArgumentException("Wrong brackets")
}

private fun pop(stack: Stack<String>, queue: MutableList<String>) {
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

private fun calcPostFix(queue: MutableList<String>): Double {
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
