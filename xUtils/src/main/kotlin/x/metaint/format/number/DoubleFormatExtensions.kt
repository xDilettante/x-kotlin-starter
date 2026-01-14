package x.metaint.format.number

import java.util.Locale
import kotlin.math.pow

/**
 * Форматирует число с фиксированным количеством знаков после запятой.
 *
 * Пример:
 *  3.14159.format(2) -> "3.14"
 */
fun Double.format(
    digits: Int = 2,
    locale: Locale = Locale.US
): String =
    String.format(locale, "%.${digits}f", this)

/**
 * Аналог для Float
 */
fun Float.format(
    digits: Int = 2,
    locale: Locale = Locale.US
): String =
    String.format(locale, "%.${digits}f", this)

/**
 * Форматирует число, убирая лишние нули справа.
 *
 *  3.5000 -> "3.5"
 *  2.0000 -> "2"
 */
fun Double.formatTrim(
    maxDigits: Int = 6,
    locale: Locale = Locale.US
): String =
    "%.${maxDigits}f"
        .format(locale, this)
        .trimEnd('0')
        .trimEnd('.')

/**
 * Округляет число до указанного количества знаков
 */
fun Double.round(digits: Int): Double =
    kotlin.math.round(this * 10.0.pow(digits)) / 10.0.pow(digits)

/**
 * Форматирует число как процент
 *
 * 0.1234 -> "12.34 %"
 */
fun Double.formatPercent(
    digits: Int = 2,
    locale: Locale = Locale.US
): String =
    String.format(locale, "%.${digits}f %%", this * 100)

/**
 * Форматирование с выравниванием (для логов, таблиц)
 */
fun Double.formatAligned(
    width: Int,
    digits: Int,
    locale: Locale = Locale.US
): String =
    String.format(locale, "%${width}.${digits}f", this)

/**
 * Безопасное форматирование для логов
 */
fun Double.formatSafe(
    digits: Int = 2,
    locale: Locale = Locale.US
): String = when {
    this.isNaN() -> "NaN"
    this == Double.POSITIVE_INFINITY -> "+Inf"
    this == Double.NEGATIVE_INFINITY -> "-Inf"
    else -> this.format(digits, locale)
}