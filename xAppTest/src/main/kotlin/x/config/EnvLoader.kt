package x.config

import io.github.cdimascio.dotenv.dotenv
import io.github.oshai.kotlinlogging.KotlinLogging
import security.isSecretKey
import security.maskSecret
import java.io.File

/**
 * Загружает переменные окружения из `.env` и выставляет их
 * в System Properties, если они ещё не заданы.
 *
 * Это позволяет Hoplite видеть их при загрузке конфигурации.
 */

private val log by lazy { KotlinLogging.logger {} }

object EnvLoader {

    fun load(logger: Boolean = true) {

        // 1️⃣ Получаем путь к подпроекту из JVM аргумента
        val projectDir = System.getProperty("project.dir")
            ?: System.getProperty("user.dir") // fallback, если аргумент не передан

        val projectName = System.getProperty("project.name")
            ?: File(projectDir).name

        // 2️⃣ Инициализируем dotenv с указанием каталога подпроекта
        val dotenv = dotenv {
            directory = projectDir
            ignoreIfMissing = false // бросит ошибку, если .env не найден
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
        }
    }
}