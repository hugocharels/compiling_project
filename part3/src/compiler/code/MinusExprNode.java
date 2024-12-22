package compiler.code;

/**
 * Represents a node for a unary minus expression in the abstract syntax tree.
 */
public class MinusExprNode implements ExprComponent {
	private final ExprComponent expr;

	/**
	 * Constructs a MinusExprNode with the given expression.
	 *
	 * @param expr the expression to negate
	 */
	public MinusExprNode(ExprComponent expr) {
		this.expr = expr;
	}

	/**
	 * Generates the LLVM code for the unary minus expression.
	 *
	 * @param llvmCode the StringBuilderWrapper to append the LLVM code to
	 * @return the temporary variable holding the result of the negation
	 */
	@Override
	public String generateLLVM(StringBuilderWrapper llvmCode) {
		String minus = expr.generateLLVM(llvmCode);
		String temp = minus;
		if (VariableManager.getInstance().isDeclared(minus)) {
			temp = llvmCode.createTempVar();
			llvmCode.appendln(String.format("%s = load i32, i32* %s, align 4", temp, minus));
		}
		String tempVar = llvmCode.createTempVar();
		llvmCode.appendln(String.format("%s = sub i32 0, %s", tempVar, temp));
		return tempVar;
	}

	/**
	 * Generates the pseudo code for the unary minus expression.
	 *
	 * @param pseudoCode the StringBuilderWrapper to append the pseudo code to
	 */
	@Override
	public void generatePseudoCode(StringBuilderWrapper pseudoCode) {
		pseudoCode.append("-");
		expr.generatePseudoCode(pseudoCode);
	}
}