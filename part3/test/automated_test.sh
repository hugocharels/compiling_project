#!/bin/bash

# ANSI color codes
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[0;33m'
NC='\033[0m' # No color

TEST_DIR="test"

# Iterate over all test files in the TEST_DIR
for input_file in $TEST_DIR/*.gls; do
    # Derive expected output file name
    base_name=$(basename "$input_file" .gls)
    actual_output="$TEST_DIR/$base_name.ll"

    # Run the program and capture the output
    echo -e "${YELLOW} Running the program on $input_file...${NC}"
    java -jar dist/part3.jar $input_file > "$actual_output"

    # Compile from ll to bc
    llvm-as "$actual_output" -o=code_source.bc

    # Run the compiled program
    lli code_source.bc

    # Clean up
    rm "$actual_output"
    rm code_source.bc
done

# Clean up compiled files
rm -rf **/*.ll