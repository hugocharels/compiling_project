import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            // Open the file or input stream to be analyzed
            FileReader reader = new FileReader(args[args.length-1]); // replace with your input file path

            // Initialize the LexicalAnalyzer with the input
            LexicalAnalyzer lexer = new LexicalAnalyzer(reader);

            // Tokenize the input and process the tokens
            Symbol symbol;
            while ((symbol = lexer.yylex()) != null) {
                // Print the token and its associated information
                System.out.println(symbol.toString());

                // Optional: handle tokens by their type (LexicalUnit)
                LexicalUnit unit = symbol.getType();
                switch (unit) {
                    case PROGNAME:
                        // Handle PROGNAME token
                        System.out.println("Program name detected: " + symbol.getValue());
                        break;
                    case VARNAME:
                        // Handle variable name token
                        System.out.println("Variable name detected: " + symbol.getValue());
                        break;
                    case NUMBER:
                        // Handle number token
                        System.out.println("Number detected: " + symbol.getValue());
                        break;
                    // Add other cases based on the LexicalUnit enums
                    default:
                        System.out.println("Other token detected: " + unit);
                }
            }

        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error during lexical analysis: " + e.getMessage());
        }
    }
}
