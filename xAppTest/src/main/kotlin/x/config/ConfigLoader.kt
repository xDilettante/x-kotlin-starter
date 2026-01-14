package x.config

import com.sksamuel.hoplite.ConfigLoaderBuilder
import com.sksamuel.hoplite.ExperimentalHoplite
import com.sksamuel.hoplite.addEnvironmentSource
import com.sksamuel.hoplite.addResourceSource
import x.config.models.AppConfig

@OptIn(ExperimentalHoplite::class)
object ConfigLoader {
    fun load(): AppConfig {
        EnvLoader.load() // загрузка .env

        val env = System.getProperty("APP_ENV") ?: "dev"
//        println("Using profile: $env  🧩")

        return ConfigLoaderBuilder.default()
            .withExplicitSealedTypes() // ✅ включаем новый режим
            .addEnvironmentSource()
            .addResourceSource("/config.yaml")
            .addResourceSource("/config-$env.yaml")
            .build()
            .loadConfigOrThrow<AppConfig>()
    }
}