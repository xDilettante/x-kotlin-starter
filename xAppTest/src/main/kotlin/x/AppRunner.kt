package x

import io.github.oshai.kotlinlogging.KotlinLogging
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeoutOrNull
import x.config.models.AppConfig

class AppRunner(private val config: AppConfig) {
    private val log by lazy { KotlinLogging.logger {} }
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Default)

    /** Стартует демо-корутину и ждёт shutdown-сигнал или таймаут. */
    suspend fun run() {
        // Загружаем конфиг и даём понять, с каким профилем работаем
        log.info { "Config Load Successful: ${config.app.name} (${config.app.env})" }

        val shutdownSignal = CompletableDeferred<Unit>()
        // Хук на SIGTERM/CTRL+C, чтобы корректно завершить корутины
        Runtime.getRuntime().addShutdownHook(
            Thread {
                log.info { "Получен сигнал завершения, останавливаем приложение..." }
                shutdownSignal.complete(Unit)
            },
        )

        val worker =
            scope.launch {
                log.info { "Запускаем демонстрационную корутину" }
                while (isActive) {
                    log.debug { "Работаем..." }
                    delay(200)
                }
            }

        // Авто-стоп через небольшой таймаут, чтобы пример не зависал при запуске
        withTimeoutOrNull(1_000) {
            shutdownSignal.await()
        }

        stop(worker)
        log.info { "Завершено" }
    }

    /** Корректно останавливает все переданные job и scope. */
    private suspend fun stop(vararg jobs: Job) {
        jobs.forEach { it.cancelAndJoin() }
        scope.cancel()
    }
}
