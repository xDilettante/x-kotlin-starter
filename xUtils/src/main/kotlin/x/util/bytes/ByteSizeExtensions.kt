package x.util.bytes

import x.util.bytes.ByteSizeConstants.GB
import x.util.bytes.ByteSizeConstants.KB
import x.util.bytes.ByteSizeConstants.MB
import x.util.bytes.ByteSizeConstants.TB

/**
 * Extension-функции для конвертации размеров данных.
 *
 * Используются бинарные единицы (1024).
 */
fun Long.toKilobytes(): Long = this / KB

fun Long.toMegabytes(): Long = this / MB

fun Long.toGigabytes(): Long = this / GB

fun Long.toTerabytes(): Long = this / TB

fun Long.kilobytesToBytes(): Long = ByteSizeConverters.kilobytesToBytes(this)

fun Long.kilobytesToMegabytes(): Long = ByteSizeConverters.kilobytesToMegabytes(this)

fun Long.kilobytesToGigabytes(): Long = ByteSizeConverters.kilobytesToGigabytes(this)

fun Long.kilobytesToTerabytes(): Long = ByteSizeConverters.kilobytesToTerabytes(this)

fun Long.megabytesToBytes(): Long = ByteSizeConverters.megabytesToBytes(this)

fun Long.megabytesToKilobytes(): Long = ByteSizeConverters.megabytesToKilobytes(this)

fun Long.megabytesToGigabytes(): Long = ByteSizeConverters.megabytesToGigabytes(this)

fun Long.megabytesToTerabytes(): Long = ByteSizeConverters.megabytesToTerabytes(this)

fun Long.gigabytesToBytes(): Long = ByteSizeConverters.gigabytesToBytes(this)

fun Long.gigabytesToKilobytes(): Long = ByteSizeConverters.gigabytesToKilobytes(this)

fun Long.gigabytesToMegabytes(): Long = ByteSizeConverters.gigabytesToMegabytes(this)

fun Long.gigabytesToTerabytes(): Long = ByteSizeConverters.gigabytesToTerabytes(this)

fun Long.terabytesToBytes(): Long = ByteSizeConverters.terabytesToBytes(this)

fun Long.terabytesToKilobytes(): Long = ByteSizeConverters.terabytesToKilobytes(this)

fun Long.terabytesToMegabytes(): Long = ByteSizeConverters.terabytesToMegabytes(this)

fun Long.terabytesToGigabytes(): Long = ByteSizeConverters.terabytesToGigabytes(this)
