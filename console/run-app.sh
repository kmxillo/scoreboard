#!/usr/bin/env bash

FILENAME=scoreboard-console.jar
LIBS_PATH=build/libs
CWD=$(dirname "$0")

if [ -f "$CWD/$FILENAME" ]
then
  JAR_FILE=$CWD/$FILENAME
else
  JAR_FILE=$LIBS_PATH/$FILENAME
fi

java -jar $JAR_FILE
