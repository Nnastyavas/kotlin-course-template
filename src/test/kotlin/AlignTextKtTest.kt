import lab1.*

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.assertThrows
import java.lang.Exception

internal class AlignTextKtTest {

    @Test
    fun testException() {
        try {
            alignText("абвгд", Alignment.LEFT, 0)
        } catch (e: IllegalArgumentException) {
            assertEquals("The string does not exist", e.message)
        }

    }

    @Test
    fun testLongWord() {
        val string: String = alignText(
            "Парацетамол", Alignment.LEFT, 3)
        val expected: String = """
            Пар
            аце
            там
            ол 
        """.trimIndent()
        assertEquals(expected, string)
    }

    @Test
    fun splitText() {
    }

    @Test
    fun testAlignLeft() {
        val string: String = alignText(
            "Я помню чудное мгновенье: " +
                    "Передо мной явилась ты, " +
                    "Как мимолетное виденье, " +
                    "Как гений чистой красоты.", Alignment.LEFT, 20
        )
        val expected: String = """
            Я помню чудное      
            мгновенье: Передо   
            мной явилась ты, Как
            мимолетное виденье, 
            Как гений чистой    
            красоты.            
        """.trimIndent()
        assertEquals(expected, string)
    }

    @Test
    fun testAlignRight() {
        val string: String = alignText(
            "Я помню чудное мгновенье: " +
                    "Передо мной явилась ты, " +
                    "Как мимолетное виденье, " +
                    "Как гений чистой красоты.", Alignment.RIGHT, 15
        )
        val expected: String = """
             Я помню чудное
                 мгновенье:
                Передо мной
            явилась ты, Как
                 мимолетное
               виденье, Как
               гений чистой
                   красоты.
        """.trimIndent()
        assertEquals(expected, string)
    }

    @Test
    fun testAlignCenter() {

            val string: String =
                alignText("Я помню чудное мгновенье: " +
                        "Передо мной явилась ты, " +
                        "Как мимолетное виденье, " +
                        "Как гений чистой красоты.", Alignment.CENTER, 15)
            val expected: String = """
                 Я помню чудное
                   мгновенье:  
                  Передо мной  
                явилась ты, Как
                   мимолетное  
                  виденье, Как 
                  гений чистой 
                    красоты.   
        """.trimIndent()
            assertEquals(expected, string)
    }
}