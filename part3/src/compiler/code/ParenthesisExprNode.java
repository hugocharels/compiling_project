package compiler.code;

public class ParenthesisExprNode implements ExprComponent {
	private final ExprComponent expr;

	public ParenthesisExprNode(ExprComponent expr) {
		this.expr = expr;
	}

	@Override
	public String generateLLVM(StringBuilderWrapper llvmCode) {
		return expr.generateLLVM(llvmCode);
	}

	@Override
	public void generatePseudoCode(StringBuilderWrapper pseudoCode) {
		pseudoCode.append("(");
		expr.generatePseudoCode(pseudoCode);
		pseudoCode.append(")");
	}
}
