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
                if (symbol.getType() == LexicalUnit.EOS) { break;}
                System.out.println(symbol.toString());
            }

        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error during lexical analysis: " + e.getMessage());
        }
    }
}
