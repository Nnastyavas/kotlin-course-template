package lab5

class LibraryService : LibraryServiceInterface {

    private val userList: MutableSet<User> = mutableSetOf()
    private val bookList: MutableMap<Book, Status> = mutableMapOf()


    override fun findBooks(author: Author): List<Book> {
        return getAllBooks().filter {
            it.author.contains(author)
        }
    }

    override fun findBooks(genre: Genre): List<Book> {
        return getAllBooks().filter {
            it.genre == genre
        }
    }

    override fun findBooks(substring: String): List<Book> {
        return getAllBooks().filter {
            it.name.contains(substring)
        }
    }

    override fun findBooks(year: Int): List<Book> {
        return getAllBooks().filter {
            it.year == year
        }
    }

    override fun findBooks(
        substring: String?,
        author: Author?,
        year: Int?,
        genre: Genre?
    ): List<Book> {
        var book = getAllBooks()
        if (substring != null) book = book.filter { it.name.contains(substring) }
        if (author != null) book = book.filter { it.author.contains(author) }
        if (year != null) book = book.filter { it.year == year }
        if (genre != null) book = book.filter { it.genre == genre }
        return book
    }

    override fun getAllBooks(): List<Book> {
        return bookList.keys.toList()
    }

    override fun getAllAvailableBooks(): List<Book> {
        return getAllBooks().filter {
            bookList[it] == Status.Available
        }
    }

    override fun getBookStatus(book: Book): Status {
        return bookList.getValue(book)
    }

    override fun getAllBookStatuses(): Map<Book, Status> {
        return bookList.toMap()
    }

    override fun setBookStatus(book: Book, status: Status) {
        if (!bookList.contains(book))
            throw NoSuchElementException("Book not found")
        bookList[book] = status
    }

    override fun addBook(book: Book, status: Status) {
        if (bookList.contains(book))
            throw IllegalArgumentException("Book was already added")
        bookList[book] = status
    }

    override fun registerUser(user: User) {
        if (userList.find { it == user } != null)
            throw IllegalStateException("User already exists")
        userList.add(user)
    }

    override fun unregisterUser(user: User) {
        if (!userList.contains(user))
            throw IllegalStateException("User does not exist")
        userList.remove(user)
    }

    override fun returnBook(book: Book) {
        if (!bookList.contains(book))
            throw NoSuchElementException("Book not found")
        bookList[book] = Status.Available
    }

    override fun takeBook(user: User, book: Book) {
        if (!userList.contains(user))
            throw NoSuchElementException ("User not found")
        if (!bookList.contains(book))
            throw NoSuchElementException ("Book not found")
        if (bookList[book] == Status.Restoration)
            throw IllegalStateException ("Book in Restoration")
        if (bookList[book] == Status.ComingSoon)
            throw IllegalStateException ("Book is coming soon")
        if (bookList.filter { it.value == Status.UsedBy(user) }.size >= 3)
            throw IllegalStateException ("User can't take more than 3 books")
        bookList[book] = Status.UsedBy(user)
    }

}