#!/usr/bin/env bash
set -euo pipefail

# Создаёт новый модуль-библиотеку на основе xEmptyLib.
# Использование: ./scripts/new-lib.sh myLib

if [[ $# -ne 1 ]]; then
  echo "Usage: $0 <new-module-name>"
  exit 1
fi

NAME="$1"
SRC_DIR="xEmptyLib"
DEST_DIR="$NAME"

if [[ -d "$DEST_DIR" ]]; then
  echo "Target dir $DEST_DIR already exists, aborting"
  exit 1
fi

cp -r "$SRC_DIR" "$DEST_DIR"

sed -i "s/xEmptyLib/$NAME/g" "$DEST_DIR/build.gradle.kts"
rm -f "$DEST_DIR/src/main/kotlin/x/Placeholder.kt"

echo "Copied $SRC_DIR to $DEST_DIR. Add to settings.gradle.kts: include(\"$NAME\")"
