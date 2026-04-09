@echo off
title Minecraft Server (SELECT JAR)
color 0a

:: ====== CONFIG RAM ======
set MIN_RAM=4G
set MAX_RAM=4G

:: ====== CHECK JAVA ======
java -version >nul 2>&1
if %errorlevel% neq 0 (
    echo [ERROR] Chua cai Java! (Can Java 17+)
    pause
    exit
)

:: ====== LOAD JAR LIST ======
set count=0
for %%i in (*.jar) do (
    set /a count+=1
    set "jar!count!=%%i"
)

if %count%==0 (
    echo [ERROR] Khong tim thay file .jar!
    pause
    exit
)

:: ====== ENABLE DELAYED EXPANSION ======
setlocal enabledelayedexpansion

:menu
cls
echo ================================
echo     CHON FILE SERVER
echo ================================
echo.

for /l %%i in (1,1,%count%) do (
    echo %%i. !jar%%i!
)

echo.
set /p choice=Nhap so de chay server: 

:: ====== VALIDATE INPUT ======
if "%choice%"=="" goto menu
if %choice% GTR %count% goto menu
if %choice% LSS 1 goto menu

set "JARFILE=!jar%choice%!"

echo.
echo Dang chay: !JARFILE!
echo RAM: %MIN_RAM% - %MAX_RAM%
echo.

:: ====== START SERVER ======
java -Xms%MIN_RAM% -Xmx%MAX_RAM% ^
-XX:+UseG1GC ^
-XX:+ParallelRefProcEnabled ^
-XX:MaxGCPauseMillis=200 ^
-XX:+UnlockExperimentalVMOptions ^
-XX:+DisableExplicitGC ^
-XX:+AlwaysPreTouch ^
-XX:G1NewSizePercent=30 ^
-XX:G1MaxNewSizePercent=40 ^
-XX:G1HeapRegionSize=8M ^
-XX:G1ReservePercent=20 ^
-XX:G1HeapWastePercent=5 ^
-XX:G1MixedGCCountTarget=4 ^
-XX:InitiatingHeapOccupancyPercent=15 ^
-XX:G1MixedGCLiveThresholdPercent=90 ^
-XX:G1RSetUpdatingPauseTimePercent=5 ^
-XX:SurvivorRatio=32 ^
-XX:+PerfDisableSharedMem ^
-XX:MaxTenuringThreshold=1 ^
-jar "!JARFILE!" nogui

echo.
echo ================================
echo   Server da tat.
echo ================================
pause 
