package compiler.code;

import compiler.GlsVariable;
import compiler.ParseTree;

/**
 * The common interface for all nodes in the code.
 */
public interface CodeComponent {

	/**
	 * Creates a CodeComponent from a parse tree.
	 *
	 * @param parseTree the parse tree to create the CodeComponent from
	 * @return the created CodeComponent
	 */
	static CodeComponent fromParseTree(ParseTree parseTree) {
		return switch (parseTree.getLabel()) {
			case GlsVariable.CODE -> CodeBlockNode.fromParseTree(parseTree);
			case GlsVariable.INSTRUCTION -> throw new UnsupportedOperationException("should not be there");
			case GlsVariable.ASSIGN -> AssignNode.fromParseTree(parseTree);
			case GlsVariable.IF -> IfNode.fromParseTree(parseTree);
			case GlsVariable.WHILE -> WhileNode.fromParseTree(parseTree);
			case GlsVariable.FOR -> ForNode.fromParseTree(parseTree);
			case GlsVariable.OUTPUT -> OutputNode.fromParseTree(parseTree);
			case GlsVariable.INPUT -> InputNode.fromParseTree(parseTree);
			default -> null;
		};
	}

	/**
	 * Generates the LLVM code for this code component.
	 *
	 * @param llvmCode the StringBuilderWrapper to append the generated LLVM code to
	 * @return the generated LLVM code as a string
	 */
	String generateLLVM(StringBuilderWrapper llvmCode);

	/**
	 * Generates the pseudo code for this code component.
	 *
	 * @param pseudoCode the StringBuilderWrapper to append the generated pseudo code to
	 */
	void generatePseudoCode(StringBuilderWrapper pseudoCode);
}