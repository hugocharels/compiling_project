package compiler.code;

import compiler.GlsTerminal;
import compiler.ParseTree;

/**
 * Composite node: Represents a block of code.
 * Implements the CodeComponent interface.
 */
public class CodeBlockNode implements CodeComponent {
	private final CodeComponent first; // The first code component in the block
	private final CodeBlockNode next; // The next code block node

	/**
	 * Constructs a CodeBlockNode with the specified first code component and next code block node.
	 *
	 * @param first the first code component in the block
	 * @param next  the next code block node
	 */
	public CodeBlockNode(CodeComponent first, CodeBlockNode next) {
		this.first = first;
		this.next = next;
	}

	/**
	 * Creates a CodeBlockNode from a parse tree.
	 *
	 * @param parseTree the parse tree to create the CodeBlockNode from
	 * @return the created CodeBlockNode
	 */
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

	/**
	 * Generates the LLVM code for this code block node.
	 *
	 * @param llvmCode the StringBuilderWrapper to append the generated LLVM code to
	 * @return null
	 */
	@Override
	public String generateLLVM(StringBuilderWrapper llvmCode) {
		first.generateLLVM(llvmCode);
		if (next != null) {
			next.generateLLVM(llvmCode);
		}
		return null;
	}

	/**
	 * Generates the pseudo code for this code block node.
	 *
	 * @param pseudoCode the StringBuilderWrapper to append the generated pseudo code to
	 */
	@Override
	public void generatePseudoCode(StringBuilderWrapper pseudoCode) {
		first.generatePseudoCode(pseudoCode);
		if (next != null) {
			next.generatePseudoCode(pseudoCode);
		}
	}
}
