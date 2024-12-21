# Variables
SRC_DIR = src
BUILD_DIR = build
FLEX_FILE = $(SRC_DIR)/compiler/LexicalAnalyzer.flex
JAVA_FILES = $(wildcard $(SRC_DIR)/**/*.java) $(wildcard $(SRC_DIR)/*.java)
LEXER_FILE = $(SRC_DIR)/compiler/LexicalAnalyzer.java
MAIN_CLASS = compiler.Main
INPUT_FILE = "test/Evaluation_algorithm.gls"
JFLEX_JAR_PATH = "dist/jflex-full-1.9.1.jar"

# Default target: compile and run
all: jar

# Compile step: first generate the LexicalAnalyzer.java from the .flex file, then compile all Java files
compile: $(LEXER_FILE)
	@echo "Compiling Java files..."
	@mkdir -p $(BUILD_DIR)
	@javac -d $(BUILD_DIR) $(shell find $(SRC_DIR) -name '*.java')

$(LEXER_FILE): $(FLEX_FILE)
	@echo "Running JFlex to generate LexicalAnalyzer.java..."
	@java -jar $(JFLEX_JAR_PATH) $(FLEX_FILE)

# Run the compiled program
run: jar
	@clear
	@echo "Running the program..."
	@java -jar dist/part3.jar $(INPUT_FILE)

jar: compile
	@echo "Creating jar file..."
	@mkdir -p dist
	@jar cvfe dist/part3.jar $(MAIN_CLASS) -C $(BUILD_DIR) .
	@make clean

clean:
	@echo "Cleaning up..."
	@rm -rf build

.PHONY: all compile run test jar clean 
