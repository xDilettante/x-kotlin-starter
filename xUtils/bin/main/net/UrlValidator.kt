package net

import java.net.URI
import java.net.URL

/**
 * Проверяет URL-строку на наличие схемы и при необходимости исправляет её, добавляя указанную схему.
 *
 * @param url URL-строка для проверки.
 * @param defaultScheme Схема, которая будет добавлена, если в URL её нет или она некорректна. По умолчанию "http".
 * @return URL Объект, представляющий исправленный и валидный URL.
 * @throws IllegalArgumentException Если строка не является корректным URL.
 */
fun checkAndFixUrl(url: String, defaultScheme: String = "http"): URL {
    return try {
        val trimmedUrl = url.trim()
        val uri = URI(trimmedUrl)
        val scheme = uri.scheme

        // Если схема уже присутствует и корректна
        if (scheme == "http" || scheme == "https") {
            uri.toURL()
        } else {
            // Добавляем схему, если она отсутствует или некорректна
            val fixedUri = URI("$defaultScheme://$trimmedUrl")
            fixedUri.toURL()
        }
    } catch (e: Exception) {
        throw IllegalArgumentException("Некорректный URL: $url", e)
    }
}

/**
 * Проверяет URL-строку на наличие схемы и при необходимости исправляет её, добавляя указанную схему.
 *
 * @receiver String URL-строка для проверки.
 * @param defaultScheme Схема, которая будет добавлена, если в URL её нет или она некорректна. По умолчанию "http".
 * @param a Исключительно для обеспечения уникальности JVM-сигнатуры. Не используется в логике функции.
 * @return URL Объект, представляющий исправленный и валидный URL.
 * @throws IllegalArgumentException Если строка не является корректным URL.
 */
fun String.checkAndFixUrl(defaultScheme: String = "http", a: Boolean = true): URL = checkAndFixUrl(this, defaultScheme)