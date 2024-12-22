package compiler.code;

/**
 * The ExprCondNode class represents a node in an expression tree that performs a conditional operation
 * on two sub-expressions. It implements the ConditionComponent interface.
 */
public class ExprCondNode implements ConditionComponent {

	private final ExprComponent left; // The left sub-expression
	private final String op; // The operator
	private final ExprComponent right; // The right sub-expression

	/**
	 * Constructs an ExprCondNode with the specified left sub-expression, operator, and right sub-expression.
	 *
	 * @param left  the left sub-expression
	 * @param op    the operator
	 * @param right the right sub-expression
	 */
	public ExprCondNode(ExprComponent left, String op, ExprComponent right) {
		this.left = left;
		this.op = op;
		this.right = right;
	}

	/**
	 * Generates the LLVM code for this conditional expression node.
	 *
	 * @param llvmCode the StringBuilderWrapper to append the generated LLVM code to
	 * @return the temporary variable holding the result of this conditional expression
	 */
	@Override
	public String generateLLVM(StringBuilderWrapper llvmCode) {

		String var1 = left.generateLLVM(llvmCode);
		String var2 = right.generateLLVM(llvmCode);
		if (VariableManager.getInstance().isDeclared(var1)) {
			var1 = llvmCode.createTempVar();
			llvmCode.append(String.format("%s = load i32, i32* ", var1));
			llvmCode.appendln(left.generateLLVM(llvmCode) + ", align 4");
		}
		if (VariableManager.getInstance().isDeclared(var2)) {
			var2 = llvmCode.createTempVar();
			llvmCode.append(String.format("%s = load i32, i32* ", var2));
			llvmCode.appendln(right.generateLLVM(llvmCode) + ", align 4");
		}

		String cond = LabelManager.getInstance().createTempCond();
		llvmCode.append("%" + cond + " = icmp " + getLLVMLogicalOperator(op) + " i32 ");
		llvmCode.appendln(var1 + ", " + var2);
		return cond;
	}

	/**
	 * Generates the pseudo code for this conditional expression node.
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