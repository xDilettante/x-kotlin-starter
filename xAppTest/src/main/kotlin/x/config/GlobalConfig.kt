package x.config

import x.config.models.AppConfig

object GlobalConfig {
    val config: AppConfig by lazy { ConfigLoader.load() }
}