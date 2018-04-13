#!/usr/bin/env bash

FILENAME=scoreboard-gui.jar
LIBS_PATH=build/libs
CWD=$(dirname "$0")

if [ -f "$CWD/$FILENAME" ]
then
  JAR_FILE=$CWD/$FILENAME
else
  JAR_FILE=$LIBS_PATH/$FILENAME
fi

javaw -jar $JAR_FILE