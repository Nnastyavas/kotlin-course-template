package lab5

data class Book(
    val name: String,
    val author: List<Author>,
    val genre: Genre,
    val year: Int
) {
    override fun toString(): String {
        return "[Book: $name, $author, $genre, $year]"
    }
}

data class Author(val firstName: String, val lastName: String) {
    override fun toString(): String {
        return "Author: $firstName $lastName"
    }
}

data class User(val firstName: String, val lastName: String) {
    override fun toString(): String {
        return " $firstName $lastName"
    }
}

enum class Genre {
    NOVEL,
    DETECTIVE,
    FANTASY,
    CLASSIC
}

sealed class Status {
    object Available : Status()
    data class UsedBy(val user: User) : Status()
    object ComingSoon : Status()
    object Restoration : Status()

    override fun toString(): String {
        return when (this) {
            is Available -> "Available"
            is ComingSoon -> "Coming soon"
            is Restoration -> "Restoration"
            is UsedBy -> "Used by ${this.user}"
        }
    }
}

interface LibraryServiceInterface {
    fun findBooks(substring: String): List<Book>
    fun findBooks(author: Author): List<Book>
    fun findBooks(year: Int): List<Book>
    fun findBooks(genre: Genre): List<Book>
    fun findBooks(
        substring: String? = null,
        author: Author? = null,
        year: Int? = null,
        genre: Genre? = null
    ): List<Book>

    fun getAllBooks(): List<Book>
    fun getAllAvailableBooks(): List<Book>

    fun getBookStatus(book: Book): Status
    fun getAllBookStatuses(): Map<Book, Status>

    fun setBookStatus(book: Book, status: Status)

    fun addBook(book: Book, status: Status = Status.Available)

    fun registerUser(user: User)
    fun unregisterUser(user: User)

    fun takeBook(user: User, book: Book)
    fun returnBook(book: Book)
}