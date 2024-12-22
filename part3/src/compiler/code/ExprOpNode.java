package compiler.code;

/**
 * The ExprOpNode class represents a node in an expression tree that performs an operation
 * on two sub-expressions. It implements the ExprComponent interface.
 */
public class ExprOpNode implements ExprComponent {
	private final ExprComponent left; // The left sub-expression
	private final String op; // The operator
	private final ExprComponent right; // The right sub-expression

	/**
	 * Constructs an ExprOpNode with the specified left sub-expression, operator, and right sub-expression.
	 *
	 * @param left  the left sub-expression
	 * @param op    the operator
	 * @param right the right sub-expression
	 */
	public ExprOpNode(ExprComponent left, String op, ExprComponent right) {
		this.left = left;
		this.op = op;
		this.right = right;
	}

	/**
	 * Generates the LLVM code for this expression node.
	 *
	 * @param llvmCode the StringBuilderWrapper to append the generated LLVM code to
	 * @return the temporary variable holding the result of this expression
	 */
	@Override
	public String generateLLVM(StringBuilderWrapper llvmCode) {
		String l = left.generateLLVM(llvmCode);
		String r = right.generateLLVM(llvmCode);
		String tempVar = llvmCode.createTempVar();
		String tempVar2 = l;
		String tempVar3 = r;
		if (VariableManager.getInstance().isDeclared(l)) {
			tempVar2 = llvmCode.createTempVar();
			llvmCode.appendln(String.format("%s = load i32, i32* %s, align 4", tempVar2, l));
		}
		if (VariableManager.getInstance().isDeclared(r)) {
			tempVar3 = llvmCode.createTempVar();
			llvmCode.appendln(String.format("%s = load i32, i32* %s, align 4", tempVar3, r));
		}
		llvmCode.appendln(String.format("%s = %s i32 %s, %s", tempVar, getLLVMOperator(op), tempVar2, tempVar3));
		return tempVar;
	}

	/**
	 * Generates the pseudo code for this expression node.
	 *
	 * @param pseudoCode the StringBuilderWrapper to append the generated pseudo code to
	 */
	@Override
	public void generatePseudoCode(StringBuilderWrapper pseudoCode) {
		left.generatePseudoCode(pseudoCode);
		pseudoCode.append(" ");
		pseudoCode.append(op);
		pseudoCode.append(" ");
		right.generatePseudoCode(pseudoCode);
	}
}