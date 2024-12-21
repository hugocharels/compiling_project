package compiler.code;

public class ExprCondNode implements ConditionComponent {

	private final ExprComponent left;
	private final String op;
	private final ExprComponent right;

	public ExprCondNode(ExprComponent left, String op, ExprComponent right) {
		this.left = left;
		this.op = op;
		this.right = right;
	}

	@Override
	public String generateLLVM(StringBuilderWrapper llvmCode) {

		String var1 = left.generateLLVM(llvmCode);
		String var2 = right.generateLLVM(llvmCode);
		if (VariableManager.getInstance().isDeclared(var1)){
			var1 = llvmCode.createTempVar();
			llvmCode.append(String.format("%s = load i32, i32* ", var1));
			llvmCode.appendln(left.generateLLVM(llvmCode) + ", align 4");
		}
		if (VariableManager.getInstance().isDeclared(var2)){
			var2 = llvmCode.createTempVar();
			llvmCode.append(String.format("%s = load i32, i32* ", var2));
			llvmCode.appendln(right.generateLLVM(llvmCode) + ", align 4");
		}

		String cond = LabelManager.getInstance().createTempCond();
		llvmCode.append("%" + cond + " = icmp " + getLLVMLogicalOperator(op) + " i32 ");
		//left.generateLLVM(llvmCode);
		llvmCode.appendln(var1 + ", " + var2);
		return cond;
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
