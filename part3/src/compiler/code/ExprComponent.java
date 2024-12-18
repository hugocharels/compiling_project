package compiler.code;

import compiler.ParseTree;

public interface ExprComponent extends CodeComponent {

	static ExprComponent fromParseTree(ParseTree parseTree) {
		return null;
	}
}
