package compiler.code;

public class MinusExprNode implements ExprComponent {
	private final ExprComponent expr;

	public MinusExprNode(ExprComponent expr) {
		this.expr = expr;
	}

	@Override
	public void generateLLVM(StringBuilder llvmCode) {
		// TODO: Implement this
		llvmCode.append("- ");
		expr.generateLLVM(llvmCode);
//		llvmCode.append("\n");
	}
}
