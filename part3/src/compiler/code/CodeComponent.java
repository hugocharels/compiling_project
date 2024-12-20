package compiler.code;

import compiler.GlsVariable;
import compiler.ParseTree;

// The common interface for all nodes
public interface CodeComponent {
	static CodeComponent fromParseTree(ParseTree parseTree) {
		return switch (parseTree.getLabel()) {
			case GlsVariable.CODE -> CodeBlockNode.fromParseTree(parseTree);
			case GlsVariable.INSTRUCTION -> throw new UnsupportedOperationException("should not be there");
			case GlsVariable.ASSIGN -> AssignNode.fromParseTree(parseTree);
			case GlsVariable.IF -> IfNode.fromParseTree(parseTree);
			case GlsVariable.WHILE -> WhileNode.fromParseTree(parseTree);
			case GlsVariable.OUTPUT -> OutputNode.fromParseTree(parseTree);
			case GlsVariable.INPUT -> InputNode.fromParseTree(parseTree);
			default -> null;
		};
	}

	void generateLLVM(StringBuilderWrapper llvmCode);

	void generatePseudoCode(StringBuilderWrapper pseudoCode);
}