package compiler.code;

import compiler.GlsVariable;
import compiler.ParseTree;

// The common interface for all nodes
interface CodeComponent {
	void generateLLVM(StringBuilder llvmCode);

	static CodeComponent fromParseTree(ParseTree parseTree) {
		// TODO: Implement this
		return switch (parseTree.getLabel()) {
			case GlsVariable.CODE -> CodeBlockNode.fromParseTree(parseTree);
			case GlsVariable.ASSIGN -> AssignNode.fromParseTree(parseTree);
			case GlsVariable.IF -> IfNode.fromParseTree(parseTree);
			case GlsVariable.WHILE -> WhileNode.fromParseTree(parseTree);
			case GlsVariable.OUTPUT -> OutputNode.fromParseTree(parseTree);
			case GlsVariable.INPUT -> InputNode.fromParseTree(parseTree);
			default -> null;
		};
	}
}