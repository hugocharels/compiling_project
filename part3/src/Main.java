import exceptions.ParsingException;

import java.io.FileReader;
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

		switch (args.length) {
			case 0:
				System.err.println("Usage:\n\tjava -jar part3.jar sourceFile.gls");
				return;
			case 1:
				inputFile = args[0];
				break;
			default:
				System.err.println("Invalid number of arguments.");
				return;
		}

		try (FileReader reader = new FileReader(inputFile)) {
			// Initialize the lexer with the input
			LexicalAnalyzer lexer = new LexicalAnalyzer(reader);
			GlsGrammar grammar = new GlsGrammar();
			Parser parser = new Parser(lexer, grammar);

			// Parse the input file
			parser.parse();

			// Generate the LLVM code
			LLVMCodeGenerator codeGenerator = new LLVMCodeGenerator(parser.getParseTree());
			codeGenerator.generateCode();

			// Write the LLVM code to standard output
			System.out.println(codeGenerator.getLLVMCode());

		} catch (IOException e) {
			System.err.println("Error reading the file: " + e.getMessage());
		} catch (ParsingException e) {
			System.err.println("Error during parsing: " + e.getMessage());
		}
	}
}
