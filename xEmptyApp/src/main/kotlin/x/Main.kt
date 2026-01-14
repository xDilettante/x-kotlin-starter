package x

import kotlinx.coroutines.runBlocking
import x.config.GlobalConfig

fun main() = runBlocking {
    val config = GlobalConfig.config
    AppRunner(config).run() // делегируем запуск и остановку приложения
}
