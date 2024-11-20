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
		Stack<GlsToken> stack = new Stack<>();
		stack.push(grammar.getStartSymbol());
		Symbol symbol = lexer.nextToken();
		GlsTerminal terminal = GlsTerminal.fromLexicalUnit(symbol.getType());
		while (!stack.isEmpty()) {
			GlsToken x = stack.peek();
			GlsVariable v = x instanceof GlsVariable ? (GlsVariable) x : null;
			if (v != null && grammar.getProduction(v, terminal) != null) {
				List<GlsToken> production = grammar.getProduction(v, terminal);
				stack.pop();
				for (int i = production.size() - 1; i >= 0; i--) {
					stack.push(production.get(i));
				}
				// TODO: Print the number of the production rule
			} else if (v== null && (GlsTerminal) x == terminal) {
				stack.pop();
				symbol = lexer.nextToken();
				terminal = GlsTerminal.fromLexicalUnit(symbol.getType());
			} else {
				throw new IOException("Syntax error");
			}
		}
	}
}