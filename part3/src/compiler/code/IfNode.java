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
						CodeBlockNode.fromParseTree(parseTree.getChild(6).getChild(0)) : null
		);
	}

	@Override
	public void generateLLVM(StringBuilder llvmCode) {
		llvmCode.append(
        """
        \t%cond = icmp HERE
        \tbr i1 %cond HERE, label %true, label %false
        \ttrue:
        \t HERE
        \tfalse:
        \t HERE
        """
		);
	}
}
