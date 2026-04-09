@echo off
title Minecraft Server (Paper/Spigot - Optimized)
color 0a

echo ================================
echo   Minecraft Server Starting...
echo ================================
echo.

:: ====== CONFIG ======
set MIN_RAM=4G
set MAX_RAM=4G
set JAR=paper.jar

:: ====== CHECK JAVA ======
java -version >nul 2>&1
if %errorlevel% neq 0 (
    echo [ERROR] Java not found! Please install Java 17+
    pause
    exit
)

echo Using RAM: %MIN_RAM% - %MAX_RAM%
echo Jar file: %JAR%
echo.

:: ====== START SERVER (AIKAR FLAGS) ======
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
-jar %JAR% nogui

echo.
echo ================================
echo   Server has stopped.
echo ================================
pause 
