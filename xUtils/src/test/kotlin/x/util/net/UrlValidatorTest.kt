package x.util.net

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test

class UrlValidatorTest {
    @Test
    fun `adds default scheme when missing`() {
        val fixed = checkAndFixUrl("example.com")
        assertEquals("http://example.com", fixed.toString())
    }

    @Test
    fun `keeps https scheme`() {
        val fixed = checkAndFixUrl("https://example.com/path")
        assertEquals("https://example.com/path", fixed.toString())
    }

    @Test
    fun `rejects unsupported schemes`() {
        assertThrows(IllegalArgumentException::class.java) {
            checkAndFixUrl("ftp://example.com")
        }
    }
}
