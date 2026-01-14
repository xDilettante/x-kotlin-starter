package x.security

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class SecretMaskingTest {
    @Test
    fun `masks long secrets keeping edges`() {
        val masked = maskSecret("abcd1234efgh5678")
        assertEquals("abcd****5678", masked)
    }

    @Test
    fun `returns stars for short secrets`() {
        val masked = maskSecret("short")
        assertEquals("****", masked)
    }
}
