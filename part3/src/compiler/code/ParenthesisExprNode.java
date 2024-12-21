package compiler.code;

public class ParenthesisExprNode implements ExprComponent {
	private final ExprComponent expr;

	public ParenthesisExprNode(ExprComponent expr) {
		this.expr = expr;
	}

	@Override
	public String generateLLVM(StringBuilderWrapper llvmCode) {
		// TODO: Implement this
		return expr.generateLLVM(llvmCode);
		//llvmCode.append("(");
		//expr.generateLLVM(llvmCode);
		//llvmCode.append(")");
		//return null;
	}

	@Override
	public void generatePseudoCode(StringBuilderWrapper pseudoCode) {
		pseudoCode.append("(");
		expr.generatePseudoCode(pseudoCode);
		pseudoCode.append(")");
	}
}
