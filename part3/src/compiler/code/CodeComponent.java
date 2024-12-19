package compiler.code;

import compiler.GlsVariable;
import compiler.ParseTree;
import compiler.Symbol;

// The common interface for all nodes
public interface CodeComponent {
	static CodeComponent fromParseTree(ParseTree parseTree) {
		// TODO: Implement this
		Symbol a = parseTree.getLabel();
		System.out.println(a);
		return switch (a) {
			case GlsVariable.CODE -> CodeBlockNode.fromParseTree(parseTree);
			case GlsVariable.INSTRUCTION -> null; //todo we only receive code and instruction from here
			case GlsVariable.ASSIGN -> AssignNode.fromParseTree(parseTree);
			case GlsVariable.IF -> IfNode.fromParseTree(parseTree);
			case GlsVariable.WHILE -> WhileNode.fromParseTree(parseTree);
			case GlsVariable.OUTPUT -> OutputNode.fromParseTree(parseTree);
			case GlsVariable.INPUT -> InputNode.fromParseTree(parseTree);
			default -> null;
		};
	}

	void generateLLVM(StringBuilder llvmCode);
}