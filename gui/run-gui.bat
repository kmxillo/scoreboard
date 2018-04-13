SET FILENAME=scoreboard-gui.jar
SET LIBS_PATH=build/libs/
SET CWD=%~p0

IF EXIST "%CWD%%FILENAME%" (
  SET JAR_FILE="%CWD%%FILENAME%"
) ELSE (
  SET JAR_FILE="%LIBS_PATH%%FILENAME%"
)

start javaw -jar %JAR_FILE%