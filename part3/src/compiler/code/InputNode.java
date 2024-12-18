package compiler.code;

import compiler.ParseTree;

public class InputNode implements CodeComponent {

	private final String variableName;

	public InputNode(String variableName) {
		this.variableName = variableName;
	}

	public static InputNode fromParseTree(ParseTree parseTree) {
		return new InputNode(parseTree.getChild(2).getLexicalSymbol().getValue().toString());
	}

	@Override
	public void generateLLVM(StringBuilder llvmCode) {
		// TODO: Implement this
	}
}
