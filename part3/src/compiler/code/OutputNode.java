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
		llvmCode.append("\t%%%s = load i32".formatted(variableName+"_final"));
		llvmCode.append(", i32* %%%s, align 4\n".formatted(variableName));
		llvmCode.append("\tcall void @println(i32 %%%s)\n".formatted(variableName+"_final"));
	}
}
