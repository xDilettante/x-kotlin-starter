#!/usr/bin/env bash
set -euo pipefail

# Создаёт новый модуль-приложение на основе xEmptyApp.
# Использование: ./scripts/new-app.sh myApp

if [[ $# -ne 1 ]]; then
  echo "Usage: $0 <new-module-name>"
  exit 1
fi

NAME="$1"
SRC_DIR="xEmptyApp"
DEST_DIR="$NAME"

if [[ -d "$DEST_DIR" ]]; then
  echo "Target dir $DEST_DIR already exists, aborting"
  exit 1
fi

cp -r "$SRC_DIR" "$DEST_DIR"

sed -i "s/xEmptyApp/$NAME/g" "$DEST_DIR/build.gradle.kts"
sed -i "s/xEmptyApp/$NAME/g" "$DEST_DIR/src/main/resources/config.yaml"
sed -i "s/xEmptyApp/$NAME/g" "$DEST_DIR/.env.example"

echo "Copied $SRC_DIR to $DEST_DIR. Add to settings.gradle.kts: include(\"$NAME\")"
