#!/bin/bash

# Script to refresh the Java project and resolve VS Code build path issues

echo "🔄 Refreshing Selenium Automation Project..."

# Clean and rebuild the project
echo "📦 Cleaning project..."
./gradlew clean

echo "🔨 Building project..."
./gradlew build

echo "📋 Refreshing dependencies..."
./gradlew --refresh-dependencies

echo "✅ Project refresh completed!"
echo ""
echo "📝 Next steps to resolve VS Code issues:"
echo "1. Reload VS Code window (Cmd+Shift+P -> 'Developer: Reload Window')"
echo "2. If issues persist, run 'Java: Reload Projects' command in VS Code"
echo "3. Check that Java Extension Pack is installed and enabled"
echo ""
echo "🧪 To run tests:"
echo "./gradlew test"
echo ""
echo "📊 To generate Allure report:"
echo "./gradlew allureReport"
