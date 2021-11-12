import lab1.*

fun main() {
  val string = """
        Скажи-ка, дядя, ведь не даром
        Москва, спаленная пожаром,
        Французу отдана?
        Ведь были ж схватки боевые,
        Да, говорят, еще какие!
        Недаром помнит вся Россия
        Про день Бородина!
    """.trimIndent()
    println("Выравнивание по центру:\n" + alignText(string, Alignment.CENTER, 10))
    println("\nВыравнивание по левому краю:\n" + alignText(string, Alignment.LEFT, 20))
    println("\nВыравнивание по правому краю:\n" + alignText(string, Alignment.RIGHT, 15))
    println("\nСлово:\n" + alignText("Аргумент", Alignment.LEFT, 3))
}