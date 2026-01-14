package x.util

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import x.util.bytes.ByteSizeConverters
import x.util.bytes.toKilobytes

class UtilHelpersTest {

    @Test
    fun `decodeHex produces expected bytes`() {
        val bytes = "0a1b".decodeHex()
        assertEquals(listOf(0x0a, 0x1b), bytes.map { it.toUByte().toInt() })
    }

    @Test
    fun `decodeBase64 returns decoded value`() {
        val decoded = "aGVsbG8=".decodeBase64()
        assertEquals("hello", decoded.toString(Charsets.UTF_8))
    }

    @Test
    fun `intToIp converts numeric to dotted`() {
        assertEquals("127.0.0.1", intToIp(0x7f000001.toInt()))
    }

    @Test
    fun `byte size converters convert bytes to mega`() {
        val mb = ByteSizeConverters.bytesToMegabytes(1_048_576)
        assertEquals(1, mb)
    }

    @Test
    fun `byte size extension converts bytes to kilobytes`() {
        assertEquals(2, 2048L.toKilobytes())
    }
}
