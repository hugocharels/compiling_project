package compiler.code;

public class ExprOpNode implements ExprComponent {
	private final ExprComponent left;
	private final String op;
	private final ExprComponent right;

	public ExprOpNode(ExprComponent left, String op, ExprComponent right) {
		this.left = left;
		this.op = op;
		this.right = right;
	}

	@Override
	public String generateLLVM(StringBuilderWrapper llvmCode) {
		if (!(left instanceof MinusExprNode || right instanceof MinusExprNode)) {
			String tempVar = llvmCode.createTempVar();
			llvmCode.appendln(String.format("%s = %s i32 %s, %s", tempVar, getLLVMOperator(op), left.generateLLVM(llvmCode), right.generateLLVM(llvmCode)));
			return tempVar;
		}
		else {
			String l = left.generateLLVM(llvmCode);
			String r = right.generateLLVM(llvmCode);
			String tempVar = llvmCode.createTempVar();
			llvmCode.appendln(String.format("%s = %s i32 %s, %s", tempVar, getLLVMOperator(op), l, r));
			return tempVar;
		}
	}

	@Override
	public void generatePseudoCode(StringBuilderWrapper pseudoCode) {
		left.generatePseudoCode(pseudoCode);
		pseudoCode.append(" ");
		pseudoCode.append(op);
		pseudoCode.append(" ");
		right.generatePseudoCode(pseudoCode);
	}
}
