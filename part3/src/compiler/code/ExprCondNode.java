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
	public void generateLLVM(StringBuilder llvmCode) {
		llvmCode.append(getLLVMOperator(op));
		llvmCode.append(" i32 ");
		left.generateLLVM(llvmCode);
		llvmCode.append(", ");
		right.generateLLVM(llvmCode);
		llvmCode.append("\n");
	}

}
