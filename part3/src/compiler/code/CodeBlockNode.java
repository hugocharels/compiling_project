package compiler.code;

import compiler.GlsTerminal;
import compiler.ParseTree;

// Composite node: Represents a block of code
public class CodeBlockNode implements CodeComponent {
	private final CodeComponent first;
	private final CodeBlockNode next;


	public CodeBlockNode(CodeComponent first, CodeBlockNode next) {
		this.first = first;
		this.next = next;
	}

	public static CodeBlockNode fromParseTree(ParseTree parseTree) {
		//⟨Code⟩ → ⟨Instruction⟩:⟨Code⟩ | ε
		//               0      1   2     0
		CodeComponent first = CodeComponent.fromParseTree(parseTree.getChild(0).getChild(0));
		if (parseTree.getChild(2).getChild(0).getLabel().equals(GlsTerminal.EPSILON)) {
			return new CodeBlockNode(first, null);
		} else {
			return new CodeBlockNode(first, fromParseTree(parseTree.getChild(2)));
		}
	}

	@Override
	public String generateLLVM(StringBuilderWrapper llvmCode) {
		first.generateLLVM(llvmCode);
		if (next != null) {
			next.generateLLVM(llvmCode);
		}
		return null;
	}

	@Override
	public void generatePseudoCode(StringBuilderWrapper pseudoCode) {
		first.generatePseudoCode(pseudoCode);
		if (next != null) {
			next.generatePseudoCode(pseudoCode);
		}
	}
}
