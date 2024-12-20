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
	public void generateLLVM(StringBuilderWrapper llvmCode) {
		llvmCode.append("%i = load i32, i32* ");
		left.generateLLVM(llvmCode);
		llvmCode.appendln(", align 4");
		llvmCode.append("%cond = icmp ");
		llvmCode.append(getLLVMLogicalOperator(op));
		llvmCode.append(" i32 ");
		//left.generateLLVM(llvmCode);
		llvmCode.append("%i");
		llvmCode.append(", ");
		right.generateLLVM(llvmCode);
		llvmCode.appendln();
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
