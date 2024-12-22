package compiler.code;

import compiler.ParseTree;

/**
 * Represents an output node in the compiler's code generation phase.
 */
public class OutputNode implements CodeComponent {
	private final String variableName;

	/**
	 * Constructs an OutputNode with the specified variable name.
	 *
	 * @param variableName the name of the variable to output
	 */
	public OutputNode(String variableName) {
		this.variableName = variableName;
	}

	/**
	 * Creates an OutputNode from a parse tree.
	 *
	 * @param parseTree the parse tree to extract the variable name from
	 * @return a new OutputNode instance
	 */
	public static OutputNode fromParseTree(ParseTree parseTree) {
		return new OutputNode(parseTree.getChild(2).getLexicalSymbol().getValue().toString());
	}

	/**
	 * Generates the LLVM code for this output node.
	 *
	 * @param llvmCode the StringBuilderWrapper to append the LLVM code to
	 * @return null (no return value needed for this operation)
	 */
	@Override
	public String generateLLVM(StringBuilderWrapper llvmCode) {
		String var = llvmCode.createTempVar();
		llvmCode.append("%s = load i32".formatted(var));
		llvmCode.appendln(", i32* %%%s, align 4".formatted(variableName));
		llvmCode.appendln("call void @println(i32 %s)".formatted(var));
		return null;
	}

	/**
	 * Generates the pseudo code for this output node.
	 *
	 * @param pseudoCode the StringBuilderWrapper to append the pseudo code to
	 */
	@Override
	public void generatePseudoCode(StringBuilderWrapper pseudoCode) {
		pseudoCode.appendln("output(%s)".formatted(variableName));
	}
}
