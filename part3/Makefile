# Variables
SRC_DIR = src
BUILD_DIR = build
FLEX_FILE = $(SRC_DIR)/compiler/LexicalAnalyzer.flex
LEXER_FILE = $(SRC_DIR)/compiler/LexicalAnalyzer.java
MAIN_CLASS = compiler.Main
INPUT_FILE = "test/Euclid.gls"
BASENAME = $(basename $(INPUT_FILE))
JFLEX_JAR_PATH = "dist/jflex-full-1.9.1.jar"

# Default target: compile and run
all: run

$(LEXER_FILE): $(FLEX_FILE)
	@clear
	@echo "Running JFlex to generate LexicalAnalyzer.java..."
	@java -jar $(JFLEX_JAR_PATH) $(FLEX_FILE)

# Compile step: first generate the LexicalAnalyzer.java from the .flex file, then compile all Java files
compile: $(LEXER_FILE)
	@echo "Compiling Java files..."
	@mkdir -p $(BUILD_DIR)
	@javac -d $(BUILD_DIR) $(shell find $(SRC_DIR) -name '*.java')

# Create a jar file with the compiled classes
jar: compile
	@echo "Creating jar file..."
	@mkdir -p dist
	@jar cvfe dist/part3.jar $(MAIN_CLASS) -C $(BUILD_DIR) .
	@make clean

# Run the compiled program
run: jar
	@clear
	@echo "Running the program..."
	@java -jar dist/part3.jar $(INPUT_FILE)

# Clean up the build directory
clean:
	@echo "Cleaning up..."
	@rm -rf build

test: jar
	@echo "Running the program with test files..."
	@./test/automated_test.sh

.PHONY: all compile jar run clean test