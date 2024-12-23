package compiler.code;

import compiler.GlsTerminal;
import compiler.ParseTree;

/**
 * Represents a 'for' loop node in the compiler's abstract syntax tree.
 */
public class ForNode implements CodeComponent {
	private final String varName;
	private final String orderSign;
	private final ExprComponent from;
	private final ExprComponent to;
	private final ExprComponent step;
	private final CodeBlockNode body;

	/**
	 * Constructs a ForNode with the specified parameters.
	 *
	 * @param varName   the name of the loop variable
	 * @param orderSign the order sign indicating the loop direction ("<" for increasing, ">" for decreasing)
	 * @param from      the starting expression of the loop
	 * @param to        the ending expression of the loop
	 * @param step      the step expression of the loop
	 * @param body      the body of the loop
	 */
	public ForNode(String varName, String orderSign, ExprComponent from, ExprComponent to, ExprComponent step, CodeBlockNode body) {
		this.varName = varName;
		this.orderSign = orderSign;
		this.from = from;
		this.to = to;
		this.step = step;
		this.body = body;
	}

	/**
	 * Creates a ForNode from a parse tree.
	 *
	 * @param parseTree the parse tree representing the 'for' loop
	 * @return a new ForNode instance
	 */
	public static ForNode fromParseTree(ParseTree parseTree) {
		// FOR { <Order> [VarName] FROM <Expr> TO <Expr> STEP <Expr> } REPEAT <Code> END
		//  0  1   2       3        4     5    6    7     8     9   10  11    12     13
		return new ForNode(
				parseTree.getChild(3).getLexicalSymbol().getValue().toString(),
				parseTree.getChild(2).getChild(0).getLabel().equals(GlsTerminal.INCREASE) ? "<" : ">",
				ExprComponent.fromParseTree(parseTree.getChild(5)),
				ExprComponent.fromParseTree(parseTree.getChild(7)),
				ExprComponent.fromParseTree(parseTree.getChild(9)),
				CodeBlockNode.fromParseTree(parseTree.getChild(12))
		);
	}

	/**
	 * Generates LLVM code for the 'for' loop.
	 *
	 * @param llvmCode the StringBuilderWrapper to append the generated LLVM code to
	 * @return null
	 */
	@Override
	public String generateLLVM(StringBuilderWrapper llvmCode) {
		// Use the assign and while node to generate the for loop
		// First, assign the from value to the variable
		AssignNode assignNode = new AssignNode(varName, from);
		assignNode.generateLLVM(llvmCode);
		// Create the while node
		WhileNode whileNode = new WhileNode(
				new ExprCondNode(new AtomNode(varName), this.orderSign, to),
				new CodeBlockNode(
						body,
						new CodeBlockNode(
								new AssignNode(
										varName,
										new ExprOpNode(new AtomNode(varName), "+", step)
								),
								null
						)
				)
		);
		whileNode.generateLLVM(llvmCode);
		return null;
	}

	/**
	 * Generates pseudo code for the 'for' loop.
	 *
	 * @param pseudoCode the StringBuilderWrapper to append the generated pseudo code to
	 */
	@Override
	public void generatePseudoCode(StringBuilderWrapper pseudoCode) {
		pseudoCode.append("for (");
		pseudoCode.append(varName);
		pseudoCode.append(" = ");
		from.generatePseudoCode(pseudoCode);
		pseudoCode.append(", ");
		pseudoCode.append(varName);
		pseudoCode.append(" ");
		pseudoCode.append(orderSign);
		pseudoCode.append(" ");
		to.generatePseudoCode(pseudoCode);
		pseudoCode.append(", ");
		pseudoCode.append(varName);
		pseudoCode.append(" += ");
		step.generatePseudoCode(pseudoCode);
		pseudoCode.appendln(") {");
		pseudoCode.incrementIndentLevel();
		body.generatePseudoCode(pseudoCode);
		pseudoCode.decrementIndentLevel();
		pseudoCode.appendln("}");
	}
}