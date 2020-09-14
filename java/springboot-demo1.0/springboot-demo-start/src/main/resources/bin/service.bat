title ÏµÍ³Ãû³Æ
@echo off

set arg1=%1%
:: echo %arg1%


set program=com.gscitylifeline.proj.tongling.web.api.Application

if "%arg1%"=="start" (
	goto funStart
) else if "%arg1%"=="stop" (
	goto funStop
) else if "%arg1%"=="kill" (
	goto funKill
) else if "%arg1%"=="restart" (
	goto funRestart
) else (
	echo "please input parameter start,stop,restart,kill"
)

:funStart
	echo "start"
	if "%OS%" == "Windows_NT" set ENV_PATH=%~dp0%
	set base_dir=%ENV_PATH%..\
	echo %base_dir%
	java -Xms1024m -Xmx1024m -XX:NewSize=256m -XX:MaxNewSize=256m -XX:MaxPermSize=128m ^
	-Dfile.encoding=UTF-8  ^
	-Dlogback.configurationFile=%base_dir%conf\logback.xml ^
	-Dspring.config.location=%base_dir%conf\application.yml ^
	-classpath "%base_dir%\lib\*;%base_dir%\conf" %program%

	goto funend

:funStop
	echo "stop"
	goto funKill
	goto funend

:funRestart
echo "restart"
	goto funKill
	goto funStart
	goto funend

:funKill
echo "kill"
	echo program: %program%
	for /f "usebackq tokens=1-2" %%a in (`jps -l ^| findstr %program%`) do (
		set pid=%%a
		set image_name=%%b
		echo %pid%
		echo %image_name%
	)
	if not defined pid (
		echo "process" %program% "does not exists"
	) else (
		echo prepare to kill %image_name%
		echo "start kill" %pid% ...
		taskkill /f /pid %pid%
	)
	goto funend
 
:funend
echo "end"

