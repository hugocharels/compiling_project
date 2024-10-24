# Variables
SRC_DIR = src
FLEX_FILE = $(SRC_DIR)/LexicalAnalyzer.flex
JAVA_FILES = $(SRC_DIR)/*.java
LEXER_FILE = $(SRC_DIR)/LexicalAnalyzer.java
CLASS_FILES = $(SRC_DIR)/*.class
MAIN_CLASS = Main
INPUT_FILE = "test/Euclid.gls"
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
run:
	@echo "Running the program..."
	@java -cp $(SRC_DIR) $(MAIN_CLASS) $(INPUT_FILE)

test: compile
	@./test/automated_test_lexer.sh

jar: compile
	@echo "Creating jar file..."
	@jar cvfe dist/part1.jar $(MAIN_CLASS) -C $(SRC_DIR) .

# Clean: remove all compiled class files
clean:
	@echo "Cleaning up..."
	@rm -f $(CLASS_FILES) 
	@rm -f $(LEXER_FILE)*
	@rm -f **/*.class

.PHONY: all compile run test jar clean 
