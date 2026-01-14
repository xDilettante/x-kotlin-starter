package x.util.bytes

import x.util.bytes.ByteSizeConstants.GB
import x.util.bytes.ByteSizeConstants.KB
import x.util.bytes.ByteSizeConstants.MB
import x.util.bytes.ByteSizeConstants.TB

object ByteSizeConverters {
    fun bytesToKilobytes(bytes: Long): Long = bytes / KB

    fun bytesToMegabytes(bytes: Long): Long = bytes / MB

    fun bytesToGigabytes(bytes: Long): Long = bytes / GB

    fun bytesToTerabytes(bytes: Long): Long = bytes / TB

    fun kilobytesToBytes(kb: Long): Long = safeMul(kb, KB)

    fun kilobytesToMegabytes(kb: Long): Long = kb / 1_024

    fun kilobytesToGigabytes(kb: Long): Long = kb / (1_024 * 1_024)

    fun kilobytesToTerabytes(kb: Long): Long = kb / (1_024 * 1_024 * 1_024)

    fun megabytesToBytes(mb: Long): Long = safeMul(mb, MB)

    fun megabytesToKilobytes(mb: Long): Long = safeMul(mb, 1_024)

    fun megabytesToGigabytes(mb: Long): Long = mb / 1_024

    fun megabytesToTerabytes(mb: Long): Long = mb / (1_024 * 1_024)

    fun gigabytesToBytes(gb: Long): Long = safeMul(gb, GB)

    fun gigabytesToKilobytes(gb: Long): Long = safeMul(gb, 1_024 * 1_024)

    fun gigabytesToMegabytes(gb: Long): Long = safeMul(gb, 1_024)

    fun gigabytesToTerabytes(gb: Long): Long = gb / 1_024

    fun terabytesToBytes(tb: Long): Long = safeMul(tb, TB)

    fun terabytesToKilobytes(tb: Long): Long = safeMul(tb, 1_024 * 1_024 * 1_024)

    fun terabytesToMegabytes(tb: Long): Long = safeMul(tb, 1_024 * 1_024)

    fun terabytesToGigabytes(tb: Long): Long = safeMul(tb, 1_024)

    private fun safeMul(
        a: Long,
        b: Long,
    ): Long {
        val result = a * b
        if (a != 0L && result / a != b) {
            throw ArithmeticException("Overflow while multiplying $a by $b")
        }
        return result
    }
}
