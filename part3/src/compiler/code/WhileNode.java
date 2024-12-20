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
	public void generateLLVM(StringBuilderWrapper llvmCode) {
		llvmCode.appendln("br label %while");
		llvmCode.appendln("while:");
		llvmCode.incrementIndentLevel();
		llvmCode.appendln("%%i = load i32, i32* %%%s, align 4".formatted("VAAR"));
		llvmCode.append("%w_cond = icmp ");
		condition.generateLLVM(llvmCode);
		condition.generateLLVM(llvmCode, "i");
		llvmCode.appendln();
		llvmCode.appendln("br i1 %w_cond, label %do, label %end");
		llvmCode.decrementIndentLevel();
		llvmCode.appendln("do:");
		llvmCode.incrementIndentLevel();
		body.generateLLVM(llvmCode);
		llvmCode.appendln("br label %while");
		llvmCode.decrementIndentLevel();
		llvmCode.appendln("end:");
	}
}
