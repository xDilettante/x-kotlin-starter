package x.util.net

import java.net.URI
import java.net.URL

/**
 * Проверяет URL-строку: если схема отсутствует — добавляет defaultScheme,
 * если схема http/https — возвращает как есть, иначе бросает исключение.
 */
fun checkAndFixUrl(
    url: String,
    defaultScheme: String = "http",
): URL {
    val trimmedUrl = url.trim()
    val uri =
        try {
            URI(trimmedUrl)
        } catch (e: Exception) {
            throw IllegalArgumentException("Некорректный URL: $url", e)
        }

    val scheme = uri.scheme
    val isHttp = scheme?.equals("http", ignoreCase = true) == true
    val isHttps = scheme?.equals("https", ignoreCase = true) == true

    return when {
        scheme == null -> URI("$defaultScheme://$trimmedUrl").toURL()
        isHttp || isHttps -> uri.toURL()
        else -> throw IllegalArgumentException("Недопустимая схема URL: $scheme")
    }
}

/**
 * Extension-обёртка для удобного вызова.
 */
fun String.checkAndFixUrl(
    defaultScheme: String = "http",
    a: Boolean = true,
): URL = checkAndFixUrl(this, defaultScheme)
