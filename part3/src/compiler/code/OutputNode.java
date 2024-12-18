package compiler.code;

import compiler.ParseTree;

public class OutputNode implements CodeComponent {
	private final String variableName;

	public OutputNode(String variableName) {
		this.variableName = variableName;
	}

	@Override
	public void generateLLVM(StringBuilder llvmCode) {
		// TODO: Implement this
	}

	public static OutputNode fromParseTree(ParseTree parseTree) {
		// TODO: Implement this
		return null;
	}
}
