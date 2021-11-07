package lab1

import java.lang.Exception

enum class Alignment{
    LEFT,
    RIGHT,
    CENTER
}

fun alignText(
    text: String,
    alignment: Alignment = Alignment.LEFT,
    lineWidth: Int = 80
): String {
    if (lineWidth < 1)
        throw Exception("The string does not exist")
    val alignTxt: MutableList<String> = splitText(text, lineWidth)
    return when (alignment) {
        Alignment.LEFT -> alignLeft(alignTxt, lineWidth)
        Alignment.RIGHT -> alignRight(alignTxt, lineWidth)
        Alignment.CENTER -> alignCenter(alignTxt, lineWidth)
    }
}

//Функция для разбивки текста по ширине страницы
fun splitText(
    textToSplit: String,
    lineWidth: Int
): MutableList<String> {
    var curText = textToSplit
    val splitText: MutableList<String> = arrayListOf()
    var indexToSplit: Int
    while (curText.isNotEmpty()) {
        indexToSplit = curText.indexOf('\n')
        if (indexToSplit !== -1 && indexToSplit <= lineWidth)
            splitText.add (curText.substring(0, indexToSplit))
        else {
            if (curText.length > lineWidth) {
                indexToSplit = curText.lastIndexOf(' ', lineWidth)
                if (indexToSplit == -1) indexToSplit = lineWidth
            } else indexToSplit = curText.lastIndex + 1
            splitText.add (curText.substring(0, indexToSplit))
        }

        var i = indexToSplit
        while (curText.length > i && (curText[i] == ' ' || curText[i] == '\n'))
            i++
        curText = curText.substring(i)
    }
    return splitText
}

fun alignLeft(alignText: MutableList<String>, lineWidth: Int): String {
    for (i in 0 until alignText.size)
        alignText[i] = alignText[i].padEnd(lineWidth)
    return alignText.joinToString(separator = "\n")
}

fun alignRight(alignText: MutableList<String>, lineWidth: Int): String {
    for (i in 0 until alignText.size)
        alignText[i] = alignText[i].padStart(lineWidth)
    return alignText.joinToString(separator = "\n")
}

fun alignCenter(alignText: MutableList<String>, lineWidth: Int): String {
    for(i in 0 until alignText.size){
        val start = lineWidth - (lineWidth - alignText[i].length)/2
        alignText[i] = alignText[i].padStart(start).padEnd(lineWidth)
    }
    return alignText.joinToString(separator = "\n")
}