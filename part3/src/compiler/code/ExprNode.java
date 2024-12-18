package compiler.code;

import compiler.ParseTree;

public class ExprNode implements ExprComponent {
	private final String value; // Can be a variable name, a number

	public ExprNode(String value) {
		this.value = value;
	}

	@Override
	public void generateLLVM(StringBuilder llvmCode) {
		llvmCode.append(value);
	}

	public static ExprNode fromParseTree(ParseTree parseTree) {
		// TODO: Implement this
		return null;
	}
}
