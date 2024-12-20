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
		// WHILE { <Cond> } REPEAT <Code> END
		//  0    1  2     3  4       5       6
		return new WhileNode(
				ConditionComponent.fromParseTree(parseTree.getChild(2)),
				CodeBlockNode.fromParseTree(parseTree.getChild(5))
		);
	}

	@Override
	public void generateLLVM(StringBuilder llvmCode) {
		// TODO: Implement this
		llvmCode.append("while ");
		condition.generateLLVM(llvmCode);
		llvmCode.append(" do\n");
		body.generateLLVM(llvmCode);
		llvmCode.append("end\n");
	}
}
