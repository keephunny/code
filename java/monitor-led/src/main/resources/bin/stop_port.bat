@echo off
setlocal enabledelayedexpansion
for /f "delims= tokens=1" %%i in ('netstat -aon ^| findstr "0.0.0.0:8081"') do (
set a=%%i
taskkill /f /pid "!a:~71,5!"
)
pause