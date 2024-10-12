import java.io.FileReader;
import java.io.IOException;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) {
        try {
            // Open the file or input stream to be analyzed
            FileReader reader = new FileReader(args[args.length - 1]); // replace with your input file path

            // Initialize the LexicalAnalyzer with the input
            LexicalAnalyzer lexer = new LexicalAnalyzer(reader);
            Map<String, Integer> variables = new HashMap<>();
            Symbol symbol;

            while ((symbol = lexer.yylex()) != null) {
                if (symbol.getType() == LexicalUnit.EOS) {
                    break;
                }
                
                // Check if the symbol is a variable
                if (symbol.getType() == LexicalUnit.VARNAME) { // Replace with actual check for your variable type
                    String variableName = symbol.getValue().toString(); // Assuming getValue() gives the variable name
                    // Store the variable if it's the first occurrence
                    variables.putIfAbsent(variableName, symbol.getLine());
                }
                System.out.println(symbol.toString());
            }

            if (variables.isEmpty()) { return; }

            // Sort the variables alphabetically and print
            Map<String, Integer> sortedVariables = new TreeMap<>(variables);
            System.out.println("\nVariables");
            for (Map.Entry<String, Integer> entry : sortedVariables.entrySet()) {
                System.out.println(entry.getKey() + "\t" + entry.getValue());
            }

        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error during lexical analysis: " + e.getMessage());
        }
    }
}
