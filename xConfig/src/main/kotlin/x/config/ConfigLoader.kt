package x.config

import com.sksamuel.hoplite.ConfigLoaderBuilder
import com.sksamuel.hoplite.ExperimentalHoplite
import com.sksamuel.hoplite.addEnvironmentSource
import com.sksamuel.hoplite.addFileSource
import com.sksamuel.hoplite.addResourceSource
import x.config.models.AppConfig
import java.io.File

@OptIn(ExperimentalHoplite::class)
object ConfigLoader {
    /** Загружает конфигурацию приложения с учётом `.env`, профиля `APP_ENV` и локальных overrides. */
    fun load(): AppConfig {
        EnvLoader.load() // загрузка .env

        val env = System.getProperty("APP_ENV") ?: "dev"

        val config =
            ConfigLoaderBuilder.default()
                .withExplicitSealedTypes() // ✅ включаем новый режим
                .addEnvironmentSource()
                .addResourceSource("/config.yaml")
                .addResourceSource("/config-$env.yaml")
                .addFileSource(File("config.local.yaml"), optional = true) // локальный override (не коммитим)
                .build()
                .loadConfigOrThrow<AppConfig>()

        applyAppProperties(config)
        applyLoggingConfig(config)

        return config
    }

    /** Проставляет свойства приложения в System properties (для logback и других потребителей). */
    private fun applyAppProperties(config: AppConfig) {
        System.setProperty("APP_ENV", config.app.env)
        System.setProperty("APP_NAME", config.app.name)
    }

    /** Прокидывает параметры логирования в System properties и создаёт каталог логов. */
    private fun applyLoggingConfig(config: AppConfig) {
        val logging = config.logging
        val logDir = logging.logDir.ifBlank { "logs" }

        // Создаём каталог для логов, чтобы RollingFileAppender не падал на старте
        File(logDir).mkdirs()

        System.setProperty("LOG_DIR", logDir)
        System.setProperty("LOG_ROOT_LEVEL", logging.rootLevel)
        System.setProperty("LOG_MAX_FILE_SIZE", logging.maxFileSize)
        System.setProperty("LOG_MAX_HISTORY", logging.maxHistory.toString())
        System.setProperty("LOG_TOTAL_SIZE_CAP", logging.totalSizeCap)

        fun threshold(
            enabled: Boolean,
            value: String,
        ) = if (enabled) value else "OFF"

        System.setProperty("LOG_CONSOLE_THRESHOLD", threshold(logging.consoleAppender.enabled, logging.consoleAppender.threshold))
        System.setProperty("LOG_TEXT_THRESHOLD", threshold(logging.fileAppender.enabled, logging.fileAppender.threshold))
        System.setProperty("LOG_JSON_THRESHOLD", threshold(logging.jsonAppender.enabled, logging.jsonAppender.threshold))
    }
}
