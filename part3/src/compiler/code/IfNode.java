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
	public void generateLLVM(StringBuilder llvmCode) {
		if (this.elseBlock == null) {
			this.generateIfEnd(llvmCode);
		} else {
			this.generateIfElse(llvmCode);
		}
	}

	private void generateIfEnd(StringBuilder llvmCode) {
		llvmCode.append("\t%cond = icmp ");
		this.condition.generateLLVM(llvmCode);
		llvmCode.append("""
				\tbr i1 %cond, label %true, label %endif
				\ttrue:
				""");
		this.thenBlock.generateLLVM(llvmCode);
		llvmCode.append("""
				\tbr label %endif
				\tendif:
				""");
	}

	private void generateIfElse(StringBuilder llvmCode) {
		llvmCode.append("\t%cond = icmp ");
		this.condition.generateLLVM(llvmCode);
		llvmCode.append("""
				\tbr i1 %cond, label %true, label %false
				\ttrue:
				""");
		this.thenBlock.generateLLVM(llvmCode);
		llvmCode.append("""
				\tbr label %endif
				false:
				""");
		this.elseBlock.generateLLVM(llvmCode);
		llvmCode.append("""
				\tbr label %endif
				\tendif:
				""");
	}
}
