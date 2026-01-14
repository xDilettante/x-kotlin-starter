package x.util.format.number

import java.util.Locale
import kotlin.math.pow

fun Double.format(
    digits: Int = 2,
    locale: Locale = Locale.US,
): String = String.format(locale, "%.${digits}f", this)

fun Float.format(
    digits: Int = 2,
    locale: Locale = Locale.US,
): String = String.format(locale, "%.${digits}f", this)

fun Double.formatTrim(
    maxDigits: Int = 6,
    locale: Locale = Locale.US,
): String =
    "%.${maxDigits}f"
        .format(locale, this)
        .trimEnd('0')
        .trimEnd('.')

fun Double.round(digits: Int): Double = kotlin.math.round(this * 10.0.pow(digits)) / 10.0.pow(digits)

fun Double.formatPercent(
    digits: Int = 2,
    locale: Locale = Locale.US,
): String = String.format(locale, "%.${digits}f %%", this * 100)

fun Double.formatAligned(
    width: Int,
    digits: Int,
    locale: Locale = Locale.US,
): String = String.format(locale, "%$width.${digits}f", this)

fun Double.formatSafe(
    digits: Int = 2,
    locale: Locale = Locale.US,
): String {
    return when {
        isNaN() -> "NaN"
        this == Double.POSITIVE_INFINITY -> "+Inf"
        this == Double.NEGATIVE_INFINITY -> "-Inf"
        else -> format(digits, locale)
    }
}
