@echo off
echo ========================================
echo IoC Container - Build and Run
echo ========================================
echo.

REM Create bin directories if they don't exist
if not exist "bin" mkdir bin
if not exist "bin\app" mkdir bin\app
if not exist "bin\ioc" mkdir bin\ioc
if not exist "bin\config" mkdir bin\config

echo [1/3] Creating directories...
echo Done!
echo.

echo [2/3] Compiling Java files...
javac -d bin src\Main.java src\ioc\*.java src\app\*.java

if %errorlevel% neq 0 (
    echo.
    echo ERROR: Compilation failed!
    echo Please check for syntax errors in your Java files.
    pause
    exit /b 1
)

echo Done!
echo.

echo [3/3] Running GUI application...
echo ========================================
echo.

java -cp bin MainGUI

echo.
echo ========================================
echo Program finished.
pause
