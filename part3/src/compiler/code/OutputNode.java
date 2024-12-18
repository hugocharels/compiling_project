package compiler.code;

import compiler.ParseTree;

public class OutputNode implements CodeComponent {
	private final String variableName;

	public OutputNode(String variableName) {
		this.variableName = variableName;
	}

	public static OutputNode fromParseTree(ParseTree parseTree) {
		return new OutputNode(parseTree.getChild(2).getLexicalSymbol().getValue().toString());
	}

	@Override
	public void generateLLVM(StringBuilder llvmCode) {
		// TODO: Implement this
	}
}
