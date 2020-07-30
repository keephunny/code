title  系统名称
@echo off

set arg1=%1%
:: echo %arg1%

set program=com.spring.project.web.api.Application

echo "start"
if "%OS%" == "Windows_NT" set ENV_PATH=%~dp0%
set base_dir=%ENV_PATH%..\
echo %base_dir%
java -Xms1024m -Xmx1024m -XX:NewSize=256m -XX:MaxNewSize=256m -XX:MaxPermSize=128m ^
-Dfile.encoding=UTF-8  ^
-Dlogback.configurationFile=%base_dir%conf\logback.xml ^
-Dspring.config.location=%base_dir%conf\application.yml ^
-classpath "%base_dir%\lib\*;%base_dir%\conf" %program%
