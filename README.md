# IoC Container 🚀

A lightweight **Inversion of Control (IoC) Container** implementation in Java with support for dependency injection, transitive dependency resolution, XML-based configuration, and priority-based implementation selection. Features both **CLI** and **GUI** interfaces!

[![Java](https://img.shields.io/badge/Java-8%2B-blue.svg)](https://www.oracle.com/java/)
[![License](https://img.shields.io/badge/License-MIT-green.svg)](LICENSE)
[![GUI](https://img.shields.io/badge/Interface-CLI%20%7C%20GUI-orange.svg)]()

## 📋 Table of Contents

- [Overview](#overview)
- [Features](#features)
- [How It Works](#how-it-works)
- [Project Structure](#project-structure)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Installation](#installation)
- [Running the Application](#running-the-application)
  - [GUI Mode (Recommended)](#gui-mode-recommended)
  - [CLI Mode](#cli-mode)
- [Configuration](#configuration)
- [Usage Examples](#usage-examples)
- [Sample Applications](#sample-applications)
- [Contributing](#contributing)
- [License](#license)

## 🎯 Overview
### 🖥️ Two User Interfaces

- **GUI Mode** (Recommended) - Modern, colorful graphical interface with Swing
- **CLI Mode** - Traditional command-line interface for terminal users

### 📱 Sample Applications

1. **Movie Manager** 🎬
   - Add movies with directors
### Core IoC Features
- ✅ **Constructor Injection** - Automatic dependency injection through constructors
- ✅ **Transitive Dependency Resolution** - Resolves nested dependencies recursively
- ✅ **XML-Based Configuration** - External configuration for flexibility
- ✅ **Priority-Based Selection** - Chooses between multiple interface implementations
- ✅ **Generic Design** - Works with any well-defined interfaces and implementations
- ✅ **Reflection-Based Resolution** - Dynamic object creation at runtime

### User Interface Features
- 🎨 **Modern GUI** - Beautiful Swing interface with gradient backgrounds
- 💻 **CLI Support** - Traditional terminal interface for command-line users
- 🖱️ **Interactive Controls** - Buttons, tables, and text areas for easy interaction
- ⚡ **One-Click Launch** - Simple batch/shell scripts to run everything
   - Real-time spell checking
   - Sample text loader
   - Demonstrates transitive dependency resolution
The implementation includes two sample applications:
1. **Movie Finder** - A movie management system with database access
2. **Spell Checker** - A text editor with spell checking capabilities

## ✨ Features

- **Constructor Injection** - Automatic dependency injection through constructors
- **Transitive Dependency Resolution** - Resolves nested dependencies recursively
- **XML-Based Configuration** - External configuration for flexibility
- **Priority-Based Selection** - Chooses between multiple interface implementations based on priority
- **Generic Design** - Works with any well-defined interfaces and implementations
- **Reflection-Based Resolution** - Dynamic object creation at runtime

## 🔧 How It Works

1. **Registration**: Components are registered with the container via XML configuration
2. **Resolution**: When a component is requested, the container:
   - Finds the highest priority implementation for the interface
   - Resolves all constructor dependencies recursively
   - Creates and returns the fully-initialized instance
3. **Injection**: Dependencies are automatically injected into constructors

```java
// The container automatically resolves all dependencies
TextEditor editor = container.resolve(TextEditor.class);
// TextEditor → SpellChecker → Dictionary → FileLoader (all auto-injected)
```CLI entry point
│   ├── MainGUI.java                   # GUI entry point ⭐
│   ├── MovieAppGUI.java               # Movie manager GUI ⭐
│   ├── SpellCheckerGUI.java           # Spell checker GUI ⭐
│   ├── ioc/
│   │   └── IoCContainer.java          # IoC container implementation
│   ├── app/
│   │   ├── MovieFinder.java           # Movie finder interface
│   │   ├── DatabaseMovieFinder.java   # Database implementation
│   │   ├── FileMovieFinder.java       # File-based implementation
│   │   ├── DatabaseAccess.java        # Database access component
│   │   ├── NetworkCommunicator.java   # Network component
│   │   ├── SpellChecker.java          # Spell checker interface
│   │   ├── EnglishSpellChecker.java   # English spell checker impl
│   │   ├── TextEditor.java            # Text editor component
│   │   ├── Dictionary.java            # Dictionary interface
│   │   ├── SimpleDictionary.java      # Simple dictionary impl
│   │   ├── FileLoader.java            # File loader interface
│   │   └── FileBasedLoader.java       # File loader implementation
│   └── config/
│       └── ioc-config.xml             # Component configuration
├── run.bat                             # Windows launcher script
├── run.sh                              # Linux/Mac launcher scriptmpl
│   │   ├── TextEditor.java            # Text editor component
│   │   ├── Dictionary.java            # Dictionary interface
│   │   ├── SimpleDictionary.java      # Simple dictionary impl
│   │   ├── FileLoader.java            # File loader interface
│   │   └── FileBasedLoader.java       # File loader implementation
│   └── config/
│       └── ioc-config.xml             # Component configuration
├── bin/                                # Compiled classes
└──**Clone the repository:**
```bash
git clone https://github.com/ziadhm/IoC-Container.git
cd IoC-Container
```

2. **That's it!** Ready to run. ✅

## 🎮 Running the Application

### GUI Mode (Recommended)

**Easiest way - Just double-click:**
- **Windows**: Double-click `run.bat`
- **Linux/Mac**: Double-click `run.sh` (or run `./run.sh` in terminal)

The script will automatically:
- ✅ Compile all Java files
- ✅ Launch the beautiful GUI
- ✅ Show any errors if compilation fails

**Manual GUI Launch:**
```bash
# Compile
javac -d bin src/*.java src/ioc/*.java src/app/*.java

# Run GUI
java -cp bin MainGUI
```

### CLI Mode

For terminal/command-line interface:
```bash
# After compilation
java -cp bin Main
```

You'll see a text menu
```bash
# Create bin directories
mkdir -p bin/app bin/ioc

# Compile all Java files
javac -d bin src/Main.java src/ioc/*.java src/app/*.java

# Run
java -cp bin Main
All components are defined in [src/config/ioc-config.xml](src/config/ioc-config.xml):

```xml
<components>
    <!-- Movie Application Components -->
    <component interface="app.MovieFinder" 
               implementation="app.DatabaseMovieFinder" 
               priority="1" />
    <component interface="app.MovieFinder" 
               implementation="app.FileMovieFinder" 
               priority="2" />
    <component interface="app.DatabaseAccess" 
               implementation="app.DatabaseAccess" 
               priority="1" />
    <component interface="app.NetworkCommunicator" 
               implementation="app.NetworkCommunicator" 
               priority="1" />

    <!-- Spell Checker Components -->
    <component interface="app.TextEditor" 
               implementation="app.TextEditor" 
               priority="1" /Finder" 
               implementation="app.FileMovieFinder" 
               priority="2" />
    
    <!-- Dependencies -->
    <component interface="app.DatabaseAccess" 
               implementation="app.DatabaseAccess" 
               priority="1" />
    <component interface="app.NetworkCommunicator" 
               implementation="app.NetworkCommunicator" 
               priority="1" />

    <!-- SpellChecker Components -->
    <component interface="app.SpellChecker" 
               implementation="app.EnglishSpellChecker" 
               priority="1" />
    <component interface="app.Dictionary" 
               implementation="app.SimpleDictionary" 
               priority="1" />
    <component interface="app.FileLoader" 
               implementation="app.FileBasedLoader" 
               priority="1" />
</components>
```

### Priority System

- **Lower priority values = Higher precedence**
- Priority `1` is the highest (default choice)
- Priority `2` is secondary, and so on
- The container selects the implementation with the lowest priority number

## 💡 Usage Examples

### Basic Usage

```java
// Initialize the container
IoCContainer container = new IoCContainer();
container.loadFromConfig("src/config/ioc-config.xml");

// Resolve a component (dependencies are auto-injected)
MovieFinder finder = container.resolve(MovieFinder.class);
finder.addMovie("Christopher Nolan", "Inception");
```

### Manual Registration

```java
// Register components programmatically
container.register(MovieFinder.class, DatabaseMovieFinder.class, 1);
container.register(DatabaseAccess.class, DatabaseAccess.class, 1);

// Resolve
MovieFinder finder = container.resolve(MovieFinder.class);
```Manager 🎬
**Features:**
- ➕ Add movies with directors
- 🔍 Search movies by director name
- 📊 View all movies in an interactive table
- 💾 In-memory database storage

**Demonstrates:**
- Multiple implementations (Database vs File-based)
- Priority-based selection
- Constructor injection with dependencies

### 2. Spell Checker 📝
**Features:**
- ✍️ Interactive text input area
- ✅ Real-time spell checking
- 📚 Built-in English dictionary
- 🔄 Sample text loader

**Demonstrates:**
- Transitive dependency resolution
- Multi-level constructor injection
- Dictionary → FileLoader → Data chain

**Ways to contribute:**
- 🐛 Report bugs
- 💡 Suggest new features
- 📝 Improve documentation
- 🔧 Submit pull requests

Feel free to check the [issues page](https://github.com/ziadhm/IoC-Container/issues).

## 📝 License

This project is open source and available under the [MIT License](LICENSE).

## 👤 Author

**Ziad Hmedan**
- GitHub: [@ziadhm](https://github.com/ziadhm)

## 🙏 Acknowledgments

- Inspired by Spring Framework's IoC Container
- Built as a learning project to understand dependency injection patterns
- Demonstrates core design principles: SOLID, IoC, and DI

---

<div align="center">

### ⭐ If you found this project helpful, please give it a star! ⭐

**Made with ☕ and Java**

</div>

2. **Create Implementation:**
```java
public class YourServiceImpl implements YourService {
    public YourServiceImpl(Dependency dep) {
        // Dependencies auto-injected
    }
    
    @Override
    public void doSomething() {
        // Your logic
    }
}
```

3. **Register in XML:**
```xml
<component interface="app.YourService" 
           implementation="app.YourServiceImpl" 
           priority="1" />
```

4. **Resolve and Use:**
```java
YourService service = container.resolve(YourService.class);
service.doSomething();
```

## 🎮 Sample Applications

### 1. Movie Application
- Add movies with directors
- Search movies by director
- List all movies in the database
- Demonstrates multi-implementation selection (Database vs File-based)

### 2. Spell Checker Application
- Check text for spelling errors
- Demonstrates transitive dependency resolution
- Shows constructor injection with multiple levels

## 📄 License

This project is open source and available under the [MIT License](LICENSE).

## 🤝 Contributing

Contributions, issues, and feature requests are welcome! Feel free to check the [issues page](https://github.com/ziadhm/IoC-Container/issues).

## 👤 Author

**Ziad**
- GitHub: [@ziadhm](https://github.com/ziadhm)

---

⭐ If you found this project helpful, please give it a star!
