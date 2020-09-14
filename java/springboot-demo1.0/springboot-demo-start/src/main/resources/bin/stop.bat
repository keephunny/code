@echo off
set program=com.spring.project.web.api.Application
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

::  netstat -aon|findstr "8086"
:: taskkill /f /pid 10060
)