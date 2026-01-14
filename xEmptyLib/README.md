# xEmptyLib — шаблон библиотеки

Как использовать:
1. Скопируйте каталог `xEmptyLib` в новый модуль и переименуйте.
2. Добавьте модуль в `settings.gradle.kts`: `include("newLib")`.
3. При необходимости обновите `group/version` и зависимости в `build.gradle.kts`.
4. Удалите `src/main/kotlin/x/Placeholder.kt` и добавьте свой код.

По умолчанию:
- Kotlin/JVM с toolchain JDK 21 (берётся из `libs.versions.toml`).
- Зависимость только на `kotlin-stdlib`.
