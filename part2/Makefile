# Variables
SRC_DIR = src
FLEX_FILE = $(SRC_DIR)/LexicalAnalyzer.flex
JAVA_FILES = $(SRC_DIR)/*.java
LEXER_FILE = $(SRC_DIR)/LexicalAnalyzer.java
CLASS_FILES = $(SRC_DIR)/*.class
MAIN_CLASS = Main
INPUT_FILE = "test/Euclid.gls"
OUTPUT_FILE = "test/Euclid.tex"
JFLEX_JAR_PATH = "dist/jflex-full-1.9.1.jar"

# Default target: compile and run
all: compile test jar

# Compile step: first generate the LexicalAnalyzer.java from the .flex file, then compile all Java files
compile: $(LEXER_FILE)
	@echo "Compiling Java files..."
	@javac $(JAVA_FILES)

# Generate LexicalAnalyzer.java from LexicalAnalyzer.flex
$(LEXER_FILE): $(FLEX_FILE)
	@echo "Running JFlex to generate LexicalAnalyzer.java..."
	@java -jar $(JFLEX_JAR_PATH) $(FLEX_FILE)

# Run the compiled program
run: jar
	@clear
	@echo "Running the program..."
	@java -jar dist/part2.jar $(INPUT_FILE)
# @java -jar dist/part2.jar -wt $(OUTPUT_FILE) $(INPUT_FILE)

jar: compile
	@echo "Creating jar file..."
	@jar cvfe dist/part2.jar $(MAIN_CLASS) -C $(SRC_DIR) .
	@make clean

# Clean: remove all compiled class files
clean:
	@echo "Cleaning up..."
	@rm -f $(CLASS_FILES)
	@rm -f **/*.class

.PHONY: all compile run test jar clean 
