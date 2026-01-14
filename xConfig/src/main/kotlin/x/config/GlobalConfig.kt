package x.config

import x.config.models.AppConfig

object GlobalConfig {
    /** Лениво загруженная конфигурация приложения. */
    val config: AppConfig by lazy { ConfigLoader.load() }
}
