package x.config.models

data class AppConfig(
    val app: AppSettings,
    val logging: LoggingConfig = LoggingConfig(),
)

data class AppSettings(
    val name: String = "AppName",
    val env: String = "dev",
)

/** Настройки логирования, которые прокидываются в logback через System properties. */
data class LoggingConfig(
    val logDir: String = "logs",
    val rootLevel: String = "TRACE",
    val maxFileSize: String = "100MB",
    val maxHistory: Int = 14,
    val totalSizeCap: String = "5GB",
    val consoleAppender: ConsoleAppender = ConsoleAppender(),
    val fileAppender: FileAppender = FileAppender(),
    val jsonAppender: JsonAppender = JsonAppender(),
)

data class ConsoleAppender(
    val enabled: Boolean = true,
    val threshold: String = "INFO",
)

data class FileAppender(
    val enabled: Boolean = true,
    val threshold: String = "INFO",
)

data class JsonAppender(
    val enabled: Boolean = true,
    val threshold: String = "INFO",
)
