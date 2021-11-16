package lab2
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*


internal class CalcKtTest {
    @Test
    fun emptyString(){
        try {
            parseExpression("     ")
        } catch (e: IllegalArgumentException) {
            assertEquals("Error. Expression can not be empty.", e.message)
        }
    }

    @Test
    fun calcBracket() {
        try {
            parseExpression("(3+2)*4)")
        } catch (e: IllegalArgumentException) {
            assertEquals("Wrong brackets", e.message)
        }
    }

    @Test
    fun wrongLetters() {
        try {
            parseExpression("3+alksdjfklsdjfkldj+asdfd+3")
        } catch (e: IllegalArgumentException) {
            assertEquals("Wrong symbols in input", e.message)
        }
    }

    @Test
    fun wrongOperatorsInStart(){
        try {
            parseExpression("*3 + 3")
        } catch (e: IllegalArgumentException){
            assertEquals("Wrong the first or the last symbol", e.message)
        }
    }

    @Test
    fun wrongOperatorsInMiddle(){
        try {
            parseExpression("+4 */ 33")
        } catch (e: IllegalArgumentException){
            assertEquals("Incorrect sequence of operations", e.message)
        }
    }

    @Test
    fun wrongOperatorsInEnd(){
        try {
            parseExpression("+4-7 ^ ")
        } catch (e: IllegalArgumentException){
            assertEquals("Wrong the first or the last symbol", e.message)
        }
    }

    @Test
    fun parsedExpression() {
        assertEquals(-1.0, parseExpression("(-(4+3)+3^+2)/-8*(3+1^6)"))
    }

    @Test
    fun simpleUnaryMinus() {
        assertEquals(1.25, parseExpression("1+2^-2"))
    }

    @Test
    fun simpleUnaryPlus() {
        assertEquals(-62.0, parseExpression("+2 - 4^+3"))
    }

    @Test
    fun unaryMinusAndPlus() {
        assertEquals(-24.0, parseExpression("-(4*3+5 - 8/ +4)-(3^+2)"))
    }

    @Test
    fun pow(){
        assertEquals(256.0, parseExpression("2^2^3"))
    }
}