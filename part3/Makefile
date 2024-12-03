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
	@cd $(SRC_DIR) && javac $(MAIN_CLASS).java

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
	@jar cvfe dist/part3.jar $(MAIN_CLASS) -C $(SRC_DIR) .
	@make clean

clean:
	@echo "Cleaning up..."
	@rm -f $(CLASS_FILES)
	@rm -f */*.class
	@rm -f */*/*.class

.PHONY: all compile run test jar clean 
