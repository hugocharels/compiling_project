package compiler.code;

import compiler.ParseTree;

/**
 * Represents a while loop node in the code.
 */
public class WhileNode implements CodeComponent {
	private final ConditionComponent condition;
	private final CodeBlockNode body;

	/**
	 * Constructs a WhileNode with the specified condition and body.
	 *
	 * @param condition the condition component of the while loop
	 * @param body      the body of the while loop
	 */
	public WhileNode(ConditionComponent condition, CodeBlockNode body) {
		this.condition = condition;
		this.body = body;
	}

	/**
	 * Creates a WhileNode from a parse tree.
	 *
	 * @param parseTree the parse tree to create the WhileNode from
	 * @return a new WhileNode instance
	 */
	public static WhileNode fromParseTree(ParseTree parseTree) {
		// WHILE { <Cond> } REPEAT <Code> END
		//  0    1  2     3   4      5     6
		return new WhileNode(
				ConditionComponent.fromParseTree(parseTree.getChild(2)),
				CodeBlockNode.fromParseTree(parseTree.getChild(5))
		);
	}

	/**
	 * Generates the LLVM code for the while loop.
	 *
	 * @param llvmCode the StringBuilderWrapper to append the LLVM code to
	 * @return null
	 */
	@Override
	public String generateLLVM(StringBuilderWrapper llvmCode) {
		LabelManager.WhileLabels whileLabels = LabelManager.getInstance().generateWhileLabels();
		llvmCode.appendln("br label %" + whileLabels.getCondLabel());
		llvmCode.appendln( whileLabels.getCondLabel() + ":");
		llvmCode.incrementIndentLevel();
		String v = condition.generateLLVM(llvmCode);
		llvmCode.appendln("br i1 %" + v + ", label %" + whileLabels.getBodyLabel() + ", label %" + whileLabels.getEndLabel());
		llvmCode.decrementIndentLevel();
		llvmCode.appendln( whileLabels.getBodyLabel() + ":");
		llvmCode.incrementIndentLevel();
		body.generateLLVM(llvmCode);
		llvmCode.appendln("br label %" + whileLabels.getCondLabel());
		llvmCode.decrementIndentLevel();
		llvmCode.appendln( whileLabels.getEndLabel() + ":");
		return null;
	}

	/**
	 * Generates the pseudo code for the while loop.
	 *
	 * @param pseudoCode the StringBuilderWrapper to append the pseudo code to
	 */
	@Override
	public void generatePseudoCode(StringBuilderWrapper pseudoCode) {
		pseudoCode.append("while (");
		condition.generatePseudoCode(pseudoCode);
		pseudoCode.appendln(") {");
		pseudoCode.incrementIndentLevel();
		body.generatePseudoCode(pseudoCode);
		pseudoCode.decrementIndentLevel();
		pseudoCode.appendln("}");
	}
}