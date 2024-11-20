import java.io.IOException;
import java.util.Stack;

public class GlsParser {
	private final LexicalAnalyzer lexer;
	private final GlsGrammar grammar;
	private ParseTree parseTree = null;
	private String leftmostDerivation = "";

	public GlsParser(LexicalAnalyzer lexer, GlsGrammar grammar) {
		this.lexer = lexer;
		this.grammar = grammar;
	}

	public ParseTree getParseTree() {
		return this.parseTree;
	}

	public String getLeftmostDerivation() {
		return this.leftmostDerivation;
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
				ProductionRule productionRule= grammar.getProduction(v, terminal);
				stack.pop();
				for (int i = productionRule.getProduction().size() - 1; i >= 0; i--) {
					stack.push(productionRule.getProduction().get(i));
				}
				this.leftmostDerivation += productionRule.getId() + " ";
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