package x.config

import io.github.cdimascio.dotenv.dotenv
import io.github.oshai.kotlinlogging.KotlinLogging
import x.security.isSecretKey
import x.security.maskSecret
import java.io.File

private val log by lazy { KotlinLogging.logger {} }

/**
 * Загружает переменные окружения из `.env` (если есть) и выставляет их в `System.getProperty`,
 * чтобы Hoplite видел значения как system properties. Секреты маскируются в логе.
 */
object EnvLoader {

    fun load(logger: Boolean = true) {

        // 1️⃣ Получаем путь к подпроекту из JVM аргумента
        val projectDir = System.getProperty("project.dir")
            ?: System.getProperty("user.dir") // fallback, если аргумент не передан
        val envFile = File(projectDir).resolve(".env")

        val projectName = System.getProperty("project.name")
            ?: File(projectDir).name

        // 2️⃣ Инициализируем dotenv с указанием каталога подпроекта
        val dotenv = dotenv {
            directory = projectDir
            ignoreIfMissing = true // в шаблоне отсутствующий .env не должен падать приложение
        }

        val added = mutableListOf<String>()

        dotenv.entries().forEach {
            if (System.getenv(it.key) == null && System.getProperty(it.key) == null) {
                System.setProperty(it.key, it.value)
                added += it.key

                val logValue = if (isSecretKey(it.key)) {
                    maskSecret(it.value)
                } else {
                    it.value
                }

                log.debug { "${it.key} = $logValue" }
            }
        }

        if (logger && added.isNotEmpty()) {
            log.info { ("🌿\tLoaded ${added.size} env vars from .env: ${added.joinToString(", ")}") }
        } else if (logger && !envFile.exists()) {
            log.warn { "Файл .env не найден в $projectName — используем только переменные среды/свойства JVM" }
        }
    }
}
