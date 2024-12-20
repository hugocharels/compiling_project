package compiler.code;

import compiler.ParseTree;

public class InputNode implements CodeComponent {

	private final String variableName;

	public InputNode(String variableName) {
		this.variableName = variableName;
	}

	public static InputNode fromParseTree(ParseTree parseTree) {
		// IN([VarName])
		// 0 1    2    3
		return new InputNode(parseTree.getChild(2).getLexicalSymbol().getValue().toString());
	}

	@Override
	public void generateLLVM(StringBuilderWrapper llvmCode) {
		llvmCode.appendln("%%%s = alloca i32, align 4".formatted(variableName));
		llvmCode.appendln("%%%s = call i32 @readInt()".formatted(variableName + "_val"));
		llvmCode.append("store i32 %%%s".formatted(variableName + "_val"));
		llvmCode.appendln(", i32* %%%s, align 4".formatted(variableName));

		VariableManager.getInstance().declare(variableName);
		VariableManager.getInstance().declare(variableName + "_val");
	}

	@Override
	public void generatePseudoCode(StringBuilderWrapper pseudoCode) {
		pseudoCode.appendln("%s = input()".formatted(variableName));
	}
}
