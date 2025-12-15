#!/bin/bash

echo "========================================"
echo "IoC Container - Build and Run"
echo "========================================"
echo ""

# Create bin directories if they don't exist
mkdir -p bin/app bin/ioc bin/config

echo "[1/3] Creating directories..."
echo "Done!"
echo ""

echo "[2/3] Compiling Java files..."
javac -d bin src/Main.java src/ioc/*.java src/app/*.java

if [ $? -ne 0 ]; then
    echo ""
    echo "ERROR: Compilation failed!"
    echo "Please check for syntax errors in your Java files."
    exit 1
fi

echo "Done!"
echo ""

echo "[3/3] Running application..."
echo "========================================"
echo ""

java -cp bin Main

echo ""
echo "========================================"
echo "Program finished."
