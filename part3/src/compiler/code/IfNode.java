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
		return new IfNode(
				ConditionComponent.fromParseTree(parseTree.getChild(2)),
				CodeBlockNode.fromParseTree(parseTree.getChild(5)),
				parseTree.getChild(6).getChild(0).getLexicalSymbol().equals(GlsTerminal.END) ? CodeBlockNode.fromParseTree(parseTree.getChild(6).getChild(0)) : null
		);
	}

	@Override
	public void generateLLVM(StringBuilder llvmCode) {
		// TODO: Implement this
	}
}
