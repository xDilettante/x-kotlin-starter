package x.metaint.bytes

/**
 * Бинарные коэффициенты для размеров данных.
 *
 * 1 KB = 1024 B
 * 1 MB = 1024 KB
 * 1 GB = 1024 MB
 * 1 TB = 1024 GB
 */
object ByteSizeConstants {
    const val KB = 1024.0
    const val MB = KB * 1024
    const val GB = MB * 1024
    const val TB = GB * 1024
}