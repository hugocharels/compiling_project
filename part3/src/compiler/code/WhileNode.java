package compiler.code;

import compiler.ParseTree;

public class WhileNode implements CodeComponent {
	private final ConditionComponent condition;
	private final CodeBlockNode body;

	public WhileNode(ConditionComponent condition, CodeBlockNode body) {
		this.condition = condition;
		this.body = body;
	}

	public static WhileNode fromParseTree(ParseTree parseTree) {
		// TODO: Implement this
		return null;
	}

	@Override
	public void generateLLVM(StringBuilder llvmCode) {
		// TODO: Implement this
	}
}
