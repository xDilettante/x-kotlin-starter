package x.logback

import ch.qos.logback.classic.Level
import ch.qos.logback.classic.Logger
import ch.qos.logback.classic.LoggerContext
import ch.qos.logback.classic.PatternLayout
import ch.qos.logback.classic.encoder.PatternLayoutEncoder
import ch.qos.logback.classic.spi.Configurator
import ch.qos.logback.classic.spi.ILoggingEvent
import ch.qos.logback.core.ConsoleAppender
import ch.qos.logback.core.spi.ContextAwareBase
import java.util.function.Supplier

/**
 * Дефолтная конфигурация Logback:
 * - ConsoleAppender
 * - паттерн с раскраской
 * - регистрация слова %xcolor(...)
 *
 * Срабатывает, если в приложении НЕТ logback.xml / logback-test.xml.
 */
class XColorLogbackConfigurator : ContextAwareBase(), Configurator {

    override fun configure(context: LoggerContext): Configurator.ExecutionStatus {
        this.context = context

        // Если в проекте есть свой logback.xml — он должен побеждать,
        // поэтому этот Configurator будет использован только при отсутствии XML.
        context.reset()

        registerConverters()

        val pattern = System.getProperty(
            "xlogback.pattern",
            "%xcolor(%-5level) %white(%d{HH:mm:ss.SSS}) %magenta(%logger{24}) %cyan([%thread]) - %xcolor(%msg%n)"
        )

        val rootLevel = System.getProperty("xlogback.level")?.let { Level.toLevel(it, Level.INFO) } ?: Level.INFO

        val encoder = PatternLayoutEncoder().apply {
            this.context = context
            this.pattern = pattern
            this.start()
        }

        val consoleAppender = ConsoleAppender<ILoggingEvent>().apply {
            this.context = context
            this.name = "XCOLOR_CONSOLE"
            this.encoder = encoder

            // На Windows/некоторых терминалах может помочь Jansi.
            // Если метода нет в твоей версии — просто проигнорируем.
            try {
                this.isWithJansi = true
            } catch (_: Throwable) {
                // ничего
            }

            this.start()
        }

        val root = context.getLogger(Logger.ROOT_LOGGER_NAME).apply {
            level = rootLevel
            detachAndStopAllAppenders()
            addAppender(consoleAppender)
        }

        addInfo("x-logback-starter: включён цветной вывод в консоль, уровень=$rootLevel")

        // Не даём примениться следующему конфигуратору (BasicConfigurator).
        return Configurator.ExecutionStatus.DO_NOT_INVOKE_NEXT_IF_ANY
    }

    private fun registerConverters() {
        // Современный способ (logback 1.3+): через supplier-map.
        PatternLayout.DEFAULT_CONVERTER_SUPPLIER_MAP["xcolor"] = Supplier { HighlightingCompositeConverterEx() }
    }
}
