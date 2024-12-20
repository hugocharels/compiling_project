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
	public void generateLLVM(StringBuilder llvmCode) {
		llvmCode.append("\t%%%s = alloca i32, align 4\n".formatted(variableName));
		llvmCode.append("\t%%%s = call i32 @readInt()\n".formatted(variableName+"_val"));
		llvmCode.append("\tstore i32 %%%s".formatted(variableName+"_val"));
		llvmCode.append(", i32* %%%s, align 4\n".formatted(variableName));
	}
}
