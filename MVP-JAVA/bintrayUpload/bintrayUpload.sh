#!/bin/zsh
cd ..
./gradlew clean build bintrayUpload -PbintrayUser=jokermk -PbintrayKey=63552899dc4276fe35eca0992ef49ca78e22a535 -PdryRun=false