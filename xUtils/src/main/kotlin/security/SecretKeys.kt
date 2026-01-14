package security


private val secretKeyMarkers = listOf(
    "PASSWORD",
    "SECRET",
    "TOKEN",
    "KEY",
    "PRIVATE",
)

/**
 * Проверяет, является ли имя переменной секретным.
 */
fun isSecretKey(key: String): Boolean =
    secretKeyMarkers.any { marker ->
        key.contains(marker, ignoreCase = true)
    }