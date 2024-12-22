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

	@Override
	public void generatePseudoCode(StringBuilderWrapper pseudoCode) {
		left.generatePseudoCode(pseudoCode);
		pseudoCode.append(" ");
		pseudoCode.append(op);
		pseudoCode.append(" ");
		right.generatePseudoCode(pseudoCode);
	}
}
