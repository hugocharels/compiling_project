import java.util.Stack;

public class Parser {
	private final LexicalAnalyzer lexer;
	private final GlsGrammar grammar = new GlsGrammar(); // TODO: Change that


	public Parser(LexicalAnalyzer lexer) {
		this.lexer = lexer;
	}

	public ParseTree getParseTree() {
		return null;
	}

	public String getLeftmostDerivation() {
		return null;
	}


	public void parse() {
		Stack<GlsToken> stack = new Stack<>();
//		stack.push(grammar.getStartSymbol());
//		Symbol symbol = lexer.nextToken();
//		while (!stack.isEmpty()) {
//			if (stack.peek() == symbol) {
//				stack.pop();
//				symbol = lexer.nextToken();
//
//			}
//		}
	}

}