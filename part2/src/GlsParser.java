import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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

		Stack<Symbol> stack = new Stack<>(); // Stack to keep track of the grammar symbols
		stack.push(grammar.getStartSymbol()); // Push the start symbol to the stack

		Stack<ParseTree> treeStack = new Stack<>(); // Stack to keep track of parse tree nodes
		this.parseTree = new ParseTree(grammar.getStartSymbol()); // Create the root of the parse tree
		treeStack.push(this.parseTree); // Push the root to the stack

		LexicalSymbol lexicalSymbol = lexer.nextToken();
		LexicalUnit terminal = lexicalSymbol.getType();

		while (!stack.isEmpty()) {

			Symbol x = stack.peek();
			ParseTree currentNode = treeStack.peek();
			GlsVariable v = x instanceof GlsVariable ? (GlsVariable) x : null;

			if (v != null && grammar.getProduction(v, terminal) != null) {
				ProductionRule productionRule = grammar.getProduction(v, terminal);
				stack.pop();
				treeStack.pop();
				for (int i = productionRule.getProduction().size() - 1; i >= 0; i--) {
					stack.push(productionRule.getProduction().get(i));
					ParseTree child = new ParseTree(productionRule.getProduction().get(i));
					currentNode.addChild(child);
					treeStack.push(child);
				}
				currentNode.reverseChildOrder();
				this.leftmostDerivation += productionRule.getId() + " ";

			} else if (v == null && x == terminal) {
				stack.pop(); // Pop the terminal from the stack
				treeStack.pop(); // Pop the terminal from the tree stack
				lexicalSymbol = lexer.nextToken(); // Get the next token
				terminal = lexicalSymbol.getType(); // Get the LexicalUnit of the next token
			} else {
				throw new IOException("Syntax error");
			}
		}
	}
}