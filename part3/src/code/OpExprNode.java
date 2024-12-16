package code;

public class OpExprNode implements ExprComponent {
	private final ExprComponent left;
	private final String op;
	private final ExprComponent right;

	public OpExprNode(ExprComponent left, String op, ExprComponent right) {
		this.left = left;
		this.op = op;
		this.right = right;
	}

	@Override
	public void generateLLVM(StringBuilder llvmCode) {
		// TODO: Implement this
	}
}
