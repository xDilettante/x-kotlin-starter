package x

import io.github.oshai.kotlinlogging.KotlinLogging
import x.config.GlobalConfig

private val log by lazy { KotlinLogging.logger {} }

fun main() {
    val config = GlobalConfig.config
    log.info { "Config Load Successful" }

    log.trace { "Running Kotlin CLI" }
    log.debug { "Running Kotlin CLI" }
    log.info { "Running Kotlin CLI" }
    log.warn { "Running Kotlin CLI" }
    log.error { "Running Kotlin CLI" }
}