package compiler.code;

public class MinusExprNode implements ExprComponent {
	private final ExprComponent expr;

	public MinusExprNode(ExprComponent expr) {
		this.expr = expr;
	}

	@Override
	public String generateLLVM(StringBuilderWrapper llvmCode) {
		String tempVar = llvmCode.createTempVar();
		llvmCode.appendln(String.format("%s = sub i32 0, %s", tempVar, expr.generateLLVM(llvmCode)));
		return tempVar;
	}

	@Override
	public void generatePseudoCode(StringBuilderWrapper pseudoCode) {
		pseudoCode.append("-");
		expr.generatePseudoCode(pseudoCode);
	}
}
