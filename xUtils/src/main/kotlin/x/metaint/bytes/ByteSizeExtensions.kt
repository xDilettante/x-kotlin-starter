package x.metaint.bytes

import x.metaint.bytes.ByteSizeConstants.GB
import x.metaint.bytes.ByteSizeConstants.KB
import x.metaint.bytes.ByteSizeConstants.MB
import x.metaint.bytes.ByteSizeConstants.TB

/**
 * Extension-функции для конвертации размеров данных.
 *
 * Используются бинарные единицы (1024).
 */

/* ===================== */
/* Long (BYTES) → ALL    */
/* ===================== */

fun Long.toKilobytes(): Double = this / KB
fun Long.toMegabytes(): Double = this / MB
fun Long.toGigabytes(): Double = this / GB
fun Long.toTerabytes(): Double = this / TB


/* ===================== */
/* Double (KILOBYTES) → ALL */
/* ===================== */

fun Double.kilobytesToBytes(): Long = (this * KB).toLong()
fun Double.kilobytesToMegabytes(): Double = this / 1024
fun Double.kilobytesToGigabytes(): Double = this / (1024 * 1024)
fun Double.kilobytesToTerabytes(): Double = this / (1024 * 1024 * 1024)


/* ===================== */
/* Double (MEGABYTES) → ALL */
/* ===================== */

fun Double.megabytesToBytes(): Long = (this * MB).toLong()
fun Double.megabytesToKilobytes(): Double = this * 1024
fun Double.megabytesToGigabytes(): Double = this / 1024
fun Double.megabytesToTerabytes(): Double = this / (1024 * 1024)


/* ===================== */
/* Double (GIGABYTES) → ALL */
/* ===================== */

fun Double.gigabytesToBytes(): Long = (this * GB).toLong()
fun Double.gigabytesToKilobytes(): Double = this * 1024 * 1024
fun Double.gigabytesToMegabytes(): Double = this * 1024
fun Double.gigabytesToTerabytes(): Double = this / 1024


/* ===================== */
/* Double (TERABYTES) → ALL */
/* ===================== */

fun Double.terabytesToBytes(): Long = (this * TB).toLong()
fun Double.terabytesToKilobytes(): Double = this * 1024 * 1024 * 1024
fun Double.terabytesToMegabytes(): Double = this * 1024 * 1024
fun Double.terabytesToGigabytes(): Double = this * 1024