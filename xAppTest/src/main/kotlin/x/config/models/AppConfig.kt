package x.config.models

data class AppConfig(
    val app: AppSettings
)

data class AppSettings(
    val name: String = "AppName",
    val env: String = "dev",
)

data class LoggingConfig(
    val logDir: String = "logs",
    val rootLevel: String = "TRACE",
    val consoleAppender: ConsoleAppender,
    val fileAppender: FileAppender,
    val jsonAppender: JsonAppender
)

data class ConsoleAppender (
    val enabled: Boolean = true,
    val threshold: String = "INFO"
)

data class FileAppender (
    val enabled: Boolean = true,
    val threshold: String = "INFO"
)

data class JsonAppender (
    val enabled: Boolean = true,
    val threshold: String = "INFO"
)