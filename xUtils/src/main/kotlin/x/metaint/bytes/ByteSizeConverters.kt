package x.metaint.bytes

/**
 * Конвертация размеров данных.
 *
 * Бинарные единицы измерения:
 * 1 KB = 1024 B
 * 1 MB = 1024 KB
 * 1 GB = 1024 MB
 * 1 TB = 1024 GB
 *
 * Подходит для:
 * - статистики трафика
 * - лимитов пользователей
 * - логирования
 * - мониторинга (Xray / VPN)
 */
object ByteSizeConverters {

    private const val KB = 1024.0
    private const val MB = KB * 1024
    private const val GB = MB * 1024
    private const val TB = GB * 1024

    /* ===================== */
    /* BYTES → ALL           */
    /* ===================== */

    fun bytesToKilobytes(bytes: Long): Double = bytes / KB
    fun bytesToMegabytes(bytes: Long): Double = bytes / MB
    fun bytesToGigabytes(bytes: Long): Double = bytes / GB
    fun bytesToTerabytes(bytes: Long): Double = bytes / TB

    /* ===================== */
    /* KILOBYTES → ALL       */
    /* ===================== */

    fun kilobytesToBytes(kb: Double): Long = (kb * KB).toLong()
    fun kilobytesToMegabytes(kb: Double): Double = kb / 1024
    fun kilobytesToGigabytes(kb: Double): Double = kb / (1024 * 1024)
    fun kilobytesToTerabytes(kb: Double): Double = kb / (1024 * 1024 * 1024)

    /* ===================== */
    /* MEGABYTES → ALL       */
    /* ===================== */

    fun megabytesToBytes(mb: Double): Long = (mb * MB).toLong()
    fun megabytesToKilobytes(mb: Double): Double = mb * 1024
    fun megabytesToGigabytes(mb: Double): Double = mb / 1024
    fun megabytesToTerabytes(mb: Double): Double = mb / (1024 * 1024)

    /* ===================== */
    /* GIGABYTES → ALL       */
    /* ===================== */

    fun gigabytesToBytes(gb: Double): Long = (gb * GB).toLong()
    fun gigabytesToKilobytes(gb: Double): Double = gb * 1024 * 1024
    fun gigabytesToMegabytes(gb: Double): Double = gb * 1024
    fun gigabytesToTerabytes(gb: Double): Double = gb / 1024

    /* ===================== */
    /* TERABYTES → ALL       */
    /* ===================== */

    fun terabytesToBytes(tb: Double): Long = (tb * TB).toLong()
    fun terabytesToKilobytes(tb: Double): Double = tb * 1024 * 1024 * 1024
    fun terabytesToMegabytes(tb: Double): Double = tb * 1024 * 1024
    fun terabytesToGigabytes(tb: Double): Double = tb * 1024
}