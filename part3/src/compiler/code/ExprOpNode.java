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
	public void generateLLVM(StringBuilder llvmCode) {
		// TODO: Implement this
		left.generateLLVM(llvmCode);
		llvmCode.append(op);
		right.generateLLVM(llvmCode);
//		llvmCode.append(" \n");
	}
}
