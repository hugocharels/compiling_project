import java.io.IOException;
import java.util.List;
import java.util.Stack;

public class GlsParser {
	private final LexicalAnalyzer lexer;
	private final GlsGrammar grammar;


	public GlsParser(LexicalAnalyzer lexer, GlsGrammar grammar) {
		this.lexer = lexer;
		this.grammar = grammar;
	}

	public ParseTree getParseTree() {
		return null;
	}

	public String getLeftmostDerivation() {
		return null;
	}


	public void parse() throws IOException {
		Stack<Symbol> stack = new Stack<>();
		stack.push(grammar.getStartSymbol());
		LexicalSymbol lexicalSymbol = lexer.nextToken();
		LexicalUnit terminal = lexicalSymbol.getType();
		while (!stack.isEmpty()) {
			Symbol x = stack.peek();
			GlsVariable v = x instanceof GlsVariable ? (GlsVariable) x : null;
			if (v != null && grammar.getProduction(v, terminal) != null) {
				List<Symbol> production = grammar.getProduction(v, terminal);
				stack.pop();
				for (int i = production.size() - 1; i >= 0; i--) {
					stack.push(production.get(i));
				}
				// TODO: Print the number of the production rule
			} else if (v == null && x == terminal) {
				stack.pop();
				lexicalSymbol = lexer.nextToken();
				terminal = lexicalSymbol.getType();
			} else {
				throw new IOException("Syntax error");
			}
		}
	}
}