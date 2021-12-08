package lab7

import java.io.File
import java.io.IOException

object FileIO {

    fun writeToFile(value: String, path: String) {
        File(path).writeText(value)
    }

    fun readFromFile(path: String): String? {
        return try {
            File(path).readText()
        } catch (e: IOException) {
            null
        }
    }
}