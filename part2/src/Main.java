import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Main Class
 */
public class Main {

	/**
	 * Analyze a given GILLES file.
	 *
	 * @param args command-line arguments
	 */
	public static void main(String[] args) {

		GlsGrammar grammar = new GlsGrammar();

		System.out.println("First sets:");
		for (GlsVariable variable : grammar.getVariables()) {
			System.out.println(variable + ": " + grammar.getFirst(variable));
		}
		System.out.println("\nFollow sets:");
		for (GlsVariable variable : grammar.getVariables()) {
			System.out.println(variable + ": " + grammar.getFollow(variable));
		}
		System.exit(0);

		if (args.length < 1) {
			System.err.println("Usage: java -jar part2.jar sourceFile.gls OR java -jar part2.jar -wt sourceFile.tex sourceFile.gls");
			return;
		}

		boolean writeToFile = false;
		String outputFile = "";
		String inputFile = "";

		if (args[0].equals("-wt")) {
			if (args.length != 3) {
				System.err.println("Invalid usage for -wt. Expected: java -jar part2.jar -wt sourceFile.tex sourceFile.gls");
				return;
			}
			writeToFile = true;
			outputFile = args[1];
			inputFile = args[2];
		} else {
			inputFile = args[0];
		}

		try (FileReader reader = new FileReader(inputFile)) {
			// Initialize the lexer with the input
			LexicalAnalyzer lexer = new LexicalAnalyzer(reader);
			Parser parser = new Parser(lexer);

			// Parse the input and build the parse tree
			ParseTree parseTree = parser.getParseTree(); // Ensure parseTree() returns a ParseTree object

			// Print the leftmost derivation to stdout
			String leftmostDerivation = parser.getLeftmostDerivation(); // Ensure this method returns a string representation
			System.out.println(leftmostDerivation);

			// Write the parse tree to the LaTeX file if -wt is specified
			if (writeToFile) {
				try (FileWriter writer = new FileWriter(outputFile)) {
					writer.write(parseTree.toLaTeX()); // Write the LaTeX representation to the output file
				}
				System.out.println("Parse tree written to: " + outputFile);
			}
		} catch (IOException e) {
			System.err.println("Error reading the file: " + e.getMessage());
		} catch (Error e) {
			System.err.println("Error during lexical or syntactic analysis: " + e.getMessage());
		}
	}
}
