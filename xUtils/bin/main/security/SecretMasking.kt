package security


/**
 * Маскирует секретные строки (токены, пароли, ключи) перед выводом в логах.
 *
 * Пример:
 *   abcdef123456 → abcd****3456
 */
fun maskSecret(value: String, visible: Int = 4): String {
    if (value.length <= visible * 4) {
        return "****"
    }

    return value.take(visible) +
            "****" +
            value.takeLast(visible)
}