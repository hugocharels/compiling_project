package compiler.code;

public class ParenthesisExprNode implements ExprComponent {
	private final ExprComponent expr;

	public ParenthesisExprNode(ExprComponent expr) {
		this.expr = expr;
	}

	@Override
	public void generateLLVM(StringBuilderWrapper llvmCode) {
		// TODO: Implement this
		llvmCode.append("(");
		expr.generateLLVM(llvmCode);
		llvmCode.append(")");
	}
}
