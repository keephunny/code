@echo off
@if not "%ECHO%" == ""  echo %ECHO%
@if "%OS%" == "Windows_NT"  setlocal

set ENV_PATH=.\
if "%OS%" == "Windows_NT" set ENV_PATH=%~dp0%

set conf_dir=%ENV_PATH%\..\conf
set logback_configurationFile=%conf_dir%\logback.xml
set springconfig=%conf_dir%\application.yml

set CLASSPATH=%conf_dir%
set CLASSPATH=%conf_dir%\..\lib\*;%CLASSPATH%

set JAVA_MEM_OPTS= -Xms1024m -Xmx1024m -XX:NewSize=256m -XX:MaxNewSize=256m -XX:MaxPermSize=128m -Dfile.encoding=UTF-8
set OTTER_OPTS= -Dlogback.configurationFile=%logback_configurationFile% -Dspring.config.location=%springconfig%

set JAVA_OPTS= %JAVA_MEM_OPTS% %OTTER_OPTS%

set CMD_STR= java %JAVA_OPTS% -classpath "%CLASSPATH%" com.spring.project.web.api.Application
echo start cmd : %CMD_STR%

java %JAVA_OPTS% -classpath "%CLASSPATH%" com.spring.project.web.api.Application