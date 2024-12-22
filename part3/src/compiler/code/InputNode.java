package compiler.code;

import compiler.ParseTree;

/**
 * Represents an input node in the code generation process.
 */
public class InputNode implements CodeComponent {

	private final String variableName;

	/**
	 * Constructs an InputNode with the specified variable name.
	 *
	 * @param variableName the name of the variable
	 */
	public InputNode(String variableName) {
		this.variableName = variableName;
	}

	/**
	 * Creates an InputNode from a parse tree.
	 *
	 * @param parseTree the parse tree
	 * @return the created InputNode
	 */
	public static InputNode fromParseTree(ParseTree parseTree) {
		// IN([VarName])
		// 0 1    2    3
		return new InputNode(parseTree.getChild(2).getLexicalSymbol().getValue().toString());
	}

	/**
	 * Generates LLVM code for the input node.
	 *
	 * @param llvmCode the StringBuilderWrapper to append the LLVM code to
	 * @return null
	 */
	@Override
	public String generateLLVM(StringBuilderWrapper llvmCode) {
		String v = llvmCode.createTempVar();
		if (!VariableManager.getInstance().isDeclared(variableName)) {
			llvmCode.appendln("%%%s = alloca i32, align 4".formatted(variableName));
			VariableManager.getInstance().declare(variableName);
		}
		llvmCode.appendln("%s = call i32 @readInt()".formatted(v));
		llvmCode.append("store i32 %s".formatted(v));
		llvmCode.appendln(", i32* %%%s, align 4".formatted(variableName));

		VariableManager.getInstance().declare(v);
		return null;
	}

	/**
	 * Generates pseudo code for the input node.
	 *
	 * @param pseudoCode the StringBuilderWrapper to append the pseudo code to
	 */
	@Override
	public void generatePseudoCode(StringBuilderWrapper pseudoCode) {
		pseudoCode.appendln("%s = input()".formatted(variableName));
	}
}
