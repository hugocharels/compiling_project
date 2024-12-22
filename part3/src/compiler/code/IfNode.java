package compiler.code;

import compiler.GlsTerminal;
import compiler.ParseTree;

/**
 * Represents an if statement node in the compiler's intermediate representation.
 */
public class IfNode implements CodeComponent {

	private final ConditionComponent condition;
	private final CodeComponent thenBlock;
	private final CodeComponent elseBlock; // Can be null

	/**
	 * Constructs an IfNode with the given condition, then block, and else block.
	 *
	 * @param condition the condition component of the if statement
	 * @param thenBlock the code block to execute if the condition is true
	 * @param elseBlock the code block to execute if the condition is false (can be null)
	 */
	public IfNode(ConditionComponent condition, CodeComponent thenBlock, CodeComponent elseBlock) {
		this.condition = condition;
		this.thenBlock = thenBlock;
		this.elseBlock = elseBlock;
	}

	/**
	 * Creates an IfNode from a parse tree.
	 *
	 * @param parseTree the parse tree representing the if statement
	 * @return a new IfNode instance
	 */
	public static IfNode fromParseTree(ParseTree parseTree) {
		//IF {⟨Cond⟩} THEN ⟨Code⟩ ⟨IfSeq⟩
		// 0 1   2  3   4     5      6
		return new IfNode(
				ConditionComponent.fromParseTree(parseTree.getChild(2)),
				CodeBlockNode.fromParseTree(parseTree.getChild(5)),
				parseTree.getChild(6).getChild(0) // ⟨IfSeq⟩ → END | ELSE
						.getLexicalSymbol().getType().toGlsTerminal().equals(GlsTerminal.END) ?
						null : CodeBlockNode.fromParseTree(parseTree.getChild(6).getChild(0))
		);
	}

	/**
	 * Generates the LLVM code for the if statement.
	 *
	 * @param llvmCode the StringBuilderWrapper to append the generated LLVM code to
	 * @return null
	 */
	@Override
	public String generateLLVM(StringBuilderWrapper llvmCode) {
		if (this.elseBlock == null) {
			this.generateIfEnd(llvmCode);
		} else {
			this.generateIfElse(llvmCode);
		}
		return null;
	}

	/**
	 * Generates the LLVM code for an if statement without an else block.
	 *
	 * @param llvmCode the StringBuilderWrapper to append the generated LLVM code to
	 */
	private void generateIfEnd(StringBuilderWrapper llvmCode) {
		LabelManager.IfLabels ifLabels = LabelManager.getInstance().generateIfLabels();
		String var = this.condition.generateLLVM(llvmCode);
		llvmCode.appendln("br i1 %" + var + ", label %" + ifLabels.getBodyLabel() + ", label %" + ifLabels.getEndLabel());
		llvmCode.appendln(ifLabels.getBodyLabel() + ":");
		llvmCode.incrementIndentLevel();
		this.thenBlock.generateLLVM(llvmCode);
		llvmCode.appendln("br label %" + ifLabels.getEndLabel());
		llvmCode.decrementIndentLevel();
		llvmCode.appendln(ifLabels.getEndLabel() + ":");
	}

	/**
	 * Generates the LLVM code for an if statement with an else block.
	 *
	 * @param llvmCode the StringBuilderWrapper to append the generated LLVM code to
	 */
	private void generateIfElse(StringBuilderWrapper llvmCode) {
		LabelManager.IfLabels ifLabels = LabelManager.getInstance().generateIfLabels();
		String var = this.condition.generateLLVM(llvmCode);
		llvmCode.appendln("br i1 %" + var + ", label %" + ifLabels.getBodyLabel() + ", label %" + ifLabels.getElseLabel());
		llvmCode.appendln(ifLabels.getBodyLabel() + ":");
		llvmCode.incrementIndentLevel();
		this.thenBlock.generateLLVM(llvmCode);
		llvmCode.appendln("br label %" + ifLabels.getEndLabel());
		llvmCode.decrementIndentLevel();
		llvmCode.appendln(ifLabels.getElseLabel() + ":");
		llvmCode.incrementIndentLevel();
		this.elseBlock.generateLLVM(llvmCode);
		llvmCode.appendln("br label %" + ifLabels.getEndLabel());
		llvmCode.decrementIndentLevel();
		llvmCode.appendln(ifLabels.getEndLabel() + ":");
	}

	/**
	 * Generates the pseudo code for the if statement.
	 *
	 * @param pseudoCode the StringBuilderWrapper to append the generated pseudo code to
	 */
	@Override
	public void generatePseudoCode(StringBuilderWrapper pseudoCode) {
		pseudoCode.append("if (");
		this.condition.generatePseudoCode(pseudoCode);
		pseudoCode.appendln(") {");
		pseudoCode.incrementIndentLevel();
		this.thenBlock.generatePseudoCode(pseudoCode);
		pseudoCode.decrementIndentLevel();
		if (this.elseBlock != null) {
			pseudoCode.appendln("} else {");
			pseudoCode.incrementIndentLevel();
			this.elseBlock.generatePseudoCode(pseudoCode);
			pseudoCode.decrementIndentLevel();
		}
		pseudoCode.appendln("}");
	}
}