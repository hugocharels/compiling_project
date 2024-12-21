package compiler.code;

import compiler.GlsTerminal;
import compiler.ParseTree;

public class IfNode implements CodeComponent {

	private final ConditionComponent condition;
	private final CodeComponent thenBlock;
	private final CodeComponent elseBlock; // Can be null

	public IfNode(ConditionComponent condition, CodeComponent thenBlock, CodeComponent elseBlock) {
		this.condition = condition;
		this.thenBlock = thenBlock;
		this.elseBlock = elseBlock;
	}

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

	@Override
	public String generateLLVM(StringBuilderWrapper llvmCode) {
		if (this.elseBlock == null) {
			this.generateIfEnd(llvmCode);
		} else {
			this.generateIfElse(llvmCode);
		}
		return null;
	}

	private void generateIfEnd(StringBuilderWrapper llvmCode) {
		//llvmCode.append("%cond = icmp ");
		this.condition.generateLLVM(llvmCode);
		llvmCode.appendln("br i1 %cond, label %true, label %endif");
		llvmCode.appendln("true:");
		llvmCode.incrementIndentLevel();
		this.thenBlock.generateLLVM(llvmCode);
		llvmCode.appendln("br label %endif");
		llvmCode.decrementIndentLevel();
		llvmCode.appendln("endif:");
	}

	private void generateIfElse(StringBuilderWrapper llvmCode) {
		//llvmCode.append("%cond = icmp ");
		this.condition.generateLLVM(llvmCode);
		llvmCode.appendln("br i1 %cond, label %true, label %false");
		llvmCode.appendln("true:");
		llvmCode.incrementIndentLevel();
		this.thenBlock.generateLLVM(llvmCode);
		llvmCode.appendln("br label %endif");
		llvmCode.decrementIndentLevel();
		llvmCode.appendln("false:");
		llvmCode.incrementIndentLevel();
		this.elseBlock.generateLLVM(llvmCode);
		llvmCode.append("br label %endif");
		llvmCode.decrementIndentLevel();
		llvmCode.appendln("endif:");
	}

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
