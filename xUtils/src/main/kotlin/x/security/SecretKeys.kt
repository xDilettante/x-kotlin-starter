package x.security

private val secretKeyMarkers =
    listOf(
        "PASSWORD",
        "SECRET",
        "TOKEN",
        "KEY",
        "PRIVATE",
        "API",
        "ACCESS",
        "AUTH",
        "SESSION",
        "BEARER",
        "CREDENTIAL",
        "COOKIE",
        "DB",
    )

/**
 * Проверяет, является ли имя переменной секретным (по маркерам).
 */
fun isSecretKey(key: String): Boolean =
    secretKeyMarkers.any { marker ->
        key.contains(marker, ignoreCase = true)
    }
