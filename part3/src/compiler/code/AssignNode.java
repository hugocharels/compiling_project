package compiler.code;

public class AssignNode implements CodeComponent {
	private final String varName;
	private final ExprComponent expr;

	public AssignNode(String varName, ExprComponent expr) {
		this.varName = varName;
		this.expr = expr;
	}

	@Override
	public void generateLLVM(StringBuilder llvmCode) {
		// TODO: assign the value given by the expression to the variable
	}
}
