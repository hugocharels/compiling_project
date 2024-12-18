package compiler.code;

import compiler.ParseTree;

// The common interface for all nodes
interface CodeComponent {
	void generateLLVM(StringBuilder llvmCode);

	static CodeComponent fromParseTree(ParseTree parseTree) {
		return null;
	}
}