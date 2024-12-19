package compiler.code;

import compiler.GlsVariable;
import compiler.ParseTree;
import compiler.Symbol;

// The common interface for all nodes
public interface CodeComponent {
	static CodeComponent fromParseTree(ParseTree parseTree) {
		// TODO: Implement this
		return switch (parseTree.getLabel()) {
			case GlsVariable.CODE -> CodeBlockNode.fromParseTree(parseTree);
			case GlsVariable.INSTRUCTION -> {
				ParseTree first = parseTree.getChild(0);
				yield switch (first.getLabel()) {
					case GlsVariable.ASSIGN -> AssignNode.fromParseTree(first);
					case GlsVariable.IF -> IfNode.fromParseTree(first);
					case GlsVariable.WHILE -> WhileNode.fromParseTree(first);
					case GlsVariable.OUTPUT -> OutputNode.fromParseTree(first);
					case GlsVariable.INPUT -> InputNode.fromParseTree(first);
                    default -> null;
                };
			}
			default -> null;
		};
	}

	void generateLLVM(StringBuilder llvmCode);
}