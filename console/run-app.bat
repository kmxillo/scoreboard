SET FILENAME=scoreboard-console.jar
SET LIBS_PATH=build/libs/
SET CWD=%~p0

IF EXIST "%CWD%%FILENAME%" (
  SET JAR_FILE=%CWD%%FILENAME%
) ELSE (
  SET JAR_FILE=%LIBS_PATH%%FILENAME%
)

start java -jar %JAR_FILE%