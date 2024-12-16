package code;

public class ParentExprNode implements ExprComponent {
	private final ExprComponent expr;

	public ParentExprNode(ExprComponent expr) {
		this.expr = expr;
	}

	@Override
	public void generateLLVM(StringBuilder llvmCode) {
		// TODO: Implement this
	}
}
