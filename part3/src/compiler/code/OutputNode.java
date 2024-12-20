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
	public void generateLLVM(StringBuilderWrapper llvmCode) {
		llvmCode.append("%%%s = load i32".formatted(variableName + "_final"));
		llvmCode.appendln(", i32* %%%s, align 4".formatted(variableName));
		llvmCode.appendln("call void @println(i32 %%%s)".formatted(variableName + "_final"));
	}
}
