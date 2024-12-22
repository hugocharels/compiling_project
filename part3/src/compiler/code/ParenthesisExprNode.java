package compiler.code;

/**
 * Represents an expression node that is enclosed in parentheses.
 */
public class ParenthesisExprNode implements ExprComponent {
	private final ExprComponent expr;

	/**
	 * Constructs a ParenthesisExprNode with the given expression.
	 *
	 * @param expr the expression to be enclosed in parentheses
	 */
	public ParenthesisExprNode(ExprComponent expr) {
		this.expr = expr;
	}

	/**
	 * Generates the LLVM code for the expression enclosed in parentheses.
	 *
	 * @param llvmCode the StringBuilderWrapper to append the LLVM code to
	 * @return the generated LLVM code as a String
	 */
	@Override
	public String generateLLVM(StringBuilderWrapper llvmCode) {
		return expr.generateLLVM(llvmCode);
	}

	/**
	 * Generates the pseudo code for the expression enclosed in parentheses.
	 *
	 * @param pseudoCode the StringBuilderWrapper to append the pseudo code to
	 */
	@Override
	public void generatePseudoCode(StringBuilderWrapper pseudoCode) {
		pseudoCode.append("(");
		expr.generatePseudoCode(pseudoCode);
		pseudoCode.append(")");
	}
}