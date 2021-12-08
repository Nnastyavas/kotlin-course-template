package lab5

fun main() {
    val library = LibraryService()
    println("Список всех книг: \n")
    val bookList = arrayListOf(
        Book("Сто лет одиночества", mutableListOf(Author("Гарсиа", "Маркес")), Genre.NOVEL, 1967),
        Book("Идиот", mutableListOf(Author("Федор", "Достоевский")), Genre.CLASSIC, 1868),
        Book("Таинственный остров", mutableListOf(Author("Жюль", "Верн")), Genre.FANTASY, 1875),
        Book("Джейн Эйр", mutableListOf(Author("Шарлотта", "Бронте")), Genre.NOVEL, 1847),
        Book("Десять негритят", mutableListOf(Author("Агата", "Кристи")), Genre.DETECTIVE, 1939),
        Book("Унесенные ветром", mutableListOf(Author("Маргарет", "Митчелл")), Genre.NOVEL, 1936),
        Book("Анна Каренина", mutableListOf(Author("Лев", "Толстой")), Genre.CLASSIC, 1878),
        Book("Поющие в терновнике", mutableListOf(Author("Колин", "Маккалоу")), Genre.NOVEL, 1977),
        Book("Бесы", mutableListOf(Author("Федор", "Достоевский")), Genre.CLASSIC, 1871)
    )
    library.addBook(bookList[0], Status.Available)
    library.addBook(bookList[1], Status.Available)
    library.addBook(bookList[2], Status.Available)
    library.addBook(bookList[3], Status.Available)
    library.addBook(bookList[4], Status.Restoration)
    library.addBook(bookList[5], Status.Available)
    library.addBook(bookList[6], Status.Available)
    library.addBook(bookList[7], Status.ComingSoon)
    library.addBook(bookList[8], Status.Available)

    library.getAllBookStatuses().forEach {
        println("${it.key} — ${it.value}")
    }

    println("\nСписок классики:\n" + library.findBooks(Genre.CLASSIC))
    println("\nРоман 1977 года: " + library.findBooks(null, null, 1977, Genre.NOVEL))

    println("\nСписок доступных книг:")
    library.getAllAvailableBooks().forEach {
        println(it)
    }

    println("\nСтатус книги Агаты Кристи Десять негритят: " + library.getBookStatus(bookList[4]))
    library.setBookStatus(bookList[4], Status.Available)
    println("Статус этой книги после изменения: " + library.getBookStatus(bookList[4]))

    val users: MutableList<User> = mutableListOf<User>(
        User("И1", "Ф1"),
        User("И2", "Ф2"),
        User("И3", "Ф3"),
        User("И4", "Ф4"),
    )
    users.forEach { library.registerUser(it) }
    println("Зарегистрированные пользователи: $users")

    library.takeBook(users[0], bookList[1])
    library.takeBook(users[0], bookList[0])
    library.takeBook(users[3], bookList[5])

    println("Список кинг после изменения статусов: ")
    library.getAllBookStatuses().forEach {
        println("${it.key} — ${it.value}")
    }

    library.unregisterUser(users[2])
    try {
        library.takeBook(users[2], bookList[3])
    } catch (e: Exception) {
        println(e.message)
    }
}
