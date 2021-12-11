package lab7

import java.io.File
import java.io.IOException

object FileIO {

    @Throws(IOException::class)
    fun writeToFile(value: String, path: String) {
        File(path).writeText(value)
    }

    @Throws(IOException::class)
    fun readFromFile(path: String): String {
        return File(path).readText()
    }
}