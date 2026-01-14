package x.util.bytes

/**
 * Бинарные коэффициенты для размеров данных.
 *
 * 1 KB = 1024 B
 * 1 MB = 1024 KB
 * 1 GB = 1024 MB
 * 1 TB = 1024 GB
 */
object ByteSizeConstants {
    const val KB: Long = 1_024L
    const val MB: Long = KB * 1_024
    const val GB: Long = MB * 1_024
    const val TB: Long = GB * 1_024
}
