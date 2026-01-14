# Contributing

## Сборка и тесты
- Полная сборка: `./gradlew build`
- Тесты модулей: `./gradlew :moduleName:test`
- Линт (не фейлит сборку по умолчанию): `./gradlew ktlintCheck`

## Конфиги и окружение
- Не коммитим `.env`, секреты, ключи. Используй `.env.example` и `config.local.yaml.example`.
- Для локальных оверрайдов: `config.local.yaml` (в .gitignore).

## Коды и PR
- Соблюдай пакет `x.*` для собственных классов (кроме генерированного gRPC).
- Добавляй KDoc/комментарии для публичных API.
- Запускай `./gradlew build` перед пушем; `ktlintCheck` поможет увидеть стиль.
