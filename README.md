# xMySample — Kotlin/Gradle шаблон

Многомодульный шаблон на Kotlin/JVM (JDK 21) с готовыми утилитами, конфигурацией через Hoplite и логированием на logback. Проект разработан с помощью ИИ (GPT Codex) как стартовая основа для новых сервисов.

## Модули
- `xUtils` — утилиты (`x.util.*`, `x.security`, `x.util.net/telegram/bytes/format`).
- `xLogging` — цветной логбек-конфигуратор + `logback.xml` по умолчанию.
- `xConfig` — загрузка конфигурации (Hoplite + `.env` + `config.local.yaml`) и прокидывание параметров логирования в System properties.
- `xRayProto` — пример protobuf/gRPC схем; генерирует код в пакетах `com.xray.*` (это нормально для proto).
- `xAppTest` — пример приложения, использует `xConfig`/`xLogging`/`xUtils`; показывает корутины + graceful shutdown.
- `xEmptyApp` — шаблон приложения (не подключен): скопируй, переименуй, добавь в `settings.gradle.kts`, есть конфиги и fat-jar задача.
- `xEmptyLib` — шаблон библиотеки (не подключен): скопируй, переименуй, добавь в `settings.gradle.kts`, убери `Placeholder.kt`.

## Быстрый старт (xAppTest)
1. Скопируй пример окружения: `cp xAppTest/.env.example xAppTest/.env` и при необходимости поправь значения.
2. (Опционально) Локальные оверрайды конфигов: `cp config.local.yaml.example config.local.yaml` — файл не коммитим, читается после `config-{env}.yaml`.
3. Запусти: `./gradlew :xAppTest:run`
4. Сборка и тесты: `./gradlew build`

Профиль выбирается через `APP_ENV` (`dev`/`prod`), конфиги лежат в `xAppTest/src/main/resources/config*.yaml`.

## Логирование
Параметры ротации и уровней берутся из конфигурации и прокидываются в системные свойства:
`LOG_DIR`, `LOG_ROOT_LEVEL`, `LOG_CONSOLE_THRESHOLD`, `LOG_TEXT_THRESHOLD`, `LOG_JSON_THRESHOLD`, `LOG_MAX_FILE_SIZE`, `LOG_MAX_HISTORY`, `LOG_TOTAL_SIZE_CAP`.

## Коррутины и shutdown
`xAppTest` (и `xEmptyApp` как шаблон) используют `CoroutineScope(SupervisorJob + Dispatchers.Default)`, хук на SIGTERM/CTRL+C и авто-таймаут, чтобы пример не зависал при запуске.

## Запуск fat JAR
`./gradlew :xAppTest:build` собирает `build/libs/xAppTest-0.0.1-all.jar` с зависимостями и манифестом `Main-Class`. Запуск:
```
java -jar build/libs/xAppTest-0.0.1-all.jar
```
Обычный `xAppTest-0.0.1.jar` — тонкий, для запуска через classpath/Gradle.

## Шаблонные модули (не подключены)
- `xEmptyApp`: скопируй каталог, переименуй модуль, добавь в `settings.gradle.kts`, поправь `group/version`/зависимости. В комплекте — пример конфигов и задача `fatJar`.
- `xEmptyLib`: скопируй каталог, переименуй модуль, добавь в `settings.gradle.kts`, удали `Placeholder.kt` и добавь свой код.

### Как создать новый сервис (из `xEmptyApp`)
1. Скопируй каталог: `cp -r xEmptyApp new-service` (или `./scripts/new-app.sh new-service`)
2. Подключи в `settings.gradle.kts`: `include("new-service")`
3. В `new-service/build.gradle.kts` поправь `group/version/mainClass` при необходимости.
4. Создай `.env` из примера: `cp new-service/.env.example new-service/.env`
5. Запусти: `./gradlew :new-service:run` или собери fat-jar: `./gradlew :new-service:build`

### Как создать новую библиотеку (из `xEmptyLib`)
1. Скопируй каталог: `cp -r xEmptyLib new-lib` (или `./scripts/new-lib.sh new-lib`)
2. Подключи в `settings.gradle.kts`: `include("new-lib")`
3. В `new-lib/build.gradle.kts` поправь `group/version` и зависимости, удали `Placeholder.kt`.
4. Собери/протести: `./gradlew :new-lib:build`

## Скрипты
- `./scripts/new-app.sh <new-module-name>` — создаёт модуль приложения из `xEmptyApp` и заменяет имя в базовых файлах.
- `./scripts/new-lib.sh <new-module-name>` — создаёт модуль библиотеки из `xEmptyLib` и удаляет `Placeholder.kt`.

## Линт (ktlint)
- Проверка стиля: `./gradlew ktlintCheck` (не фейлит сборку по умолчанию).
- Автоисправление: `./gradlew ktlintFormat`.

## Прото/gRPC
`xRayProto` генерирует код в пакетах `com.xray.*` (соответствует proto-схемам). Это не переносится под корневой пакет `x` и так и должно оставаться.

Генерация кода: `./gradlew :xRayProto:generateProto`. Сгенерированный код не коммитится, модуль добавлен как пример работы с протобуф.

## CI
В `.github/workflows/ci.yml` — базовый pipeline: JDK 21 + `./gradlew build`.
