#!/bin/bash

# Variables
SRC_DIR="src"
MAIN_CLASS="Main"
TEST_DIR="test"
FAILED_TESTS=0


# Iterate over all test files in the TEST_DIR
for input_file in $TEST_DIR/*.gls; do
    # Derive expected output file name
    base_name=$(basename "$input_file" .gls)
    expected_output="$TEST_DIR/$base_name.expected"
    actual_output="$TEST_DIR/$base_name.actual"

    # Run the program and capture the output
    echo "Running the program on $input_file..."
    java -cp $SRC_DIR $MAIN_CLASS "$input_file" > "$actual_output"

    # Compare actual output with expected output
    if diff -w "$actual_output" "$expected_output" > /dev/null; then
        echo "Test $base_name passed."
    else
        echo "Test $base_name failed: Output differs."
        echo "Differences:"
        diff -u --color "$actual_output" "$expected_output"
        FAILED_TESTS=$((FAILED_TESTS+1))
    fi
    rm "$actual_output"
done

# Display the final test summary
if [ $FAILED_TESTS -eq 0 ]; then
    echo "All tests passed successfully."
else
    echo "$FAILED_TESTS tests failed."
fi

# Clean up compiled files
make clean