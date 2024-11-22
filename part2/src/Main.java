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
		String inputFile;
		String outputFile = "";

		switch (args.length) {
			case 0:
				System.err.println("Usage:\n\tjava -jar part2.jar sourceFile.gls\n\tjava -jar part2.jar -wt sourceFile.tex sourceFile.gls");
				return;
			case 1:
				inputFile = args[0];
				break;
			case 3:
				if (!args[0].equals("-wt")) {
					System.err.println("Invalid usage for -wt. Expected: java -jar part2.jar -wt sourceFile.tex sourceFile.gls");
					return;
				}
				outputFile = args[1];
				inputFile = args[2];
				break;
			default:
				System.err.println("Invalid number of arguments.");
				return;
		}

		try (FileReader reader = new FileReader(inputFile)) {
			// Initialize the lexer with the input
			LexicalAnalyzer lexer = new LexicalAnalyzer(reader);
			GlsGrammar grammar = new GlsGrammar();
			GlsParser parser = new GlsParser(lexer, grammar);

			// System.out.println(grammar.toLatex());

			// Parse the input file
			parser.parse();

			// Print the leftmost derivation to stdout
			System.out.println(parser.getLeftmostDerivation());

			ParseTree parseTree = parser.getParseTree();

			// Write the parse tree to the LaTeX file if -wt is specified
			if (!outputFile.equals("")) {
				try (FileWriter writer = new FileWriter(outputFile)) {
					writer.write(parseTree.toLaTeX()); // Write the LaTeX representation to the output file
					// writer.write(parseTree.toTikZPicture()); // Write the TikZ representation to the output file
				}
			}
		} catch (IOException e) {
			System.err.println("Error reading the file: " + e.getMessage());
		} catch (Error e) {
			System.err.println("Error during lexical or syntactic analysis: " + e.getMessage());
		}
	}
}
