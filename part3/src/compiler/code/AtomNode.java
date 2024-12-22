package compiler.code;

/**
 * The AtomNode class represents a node in an expression tree that holds a single value.
 * It implements the ExprComponent interface.
 */
public class AtomNode implements ExprComponent {
	private final String value; // Can be a variable name or a number

	/**
	 * Constructs an AtomNode with the specified value.
	 *
	 * @param value the value of the node, which can be a variable name or a number
	 */
	public AtomNode(String value) {
		this.value = value;
	}

	/**
	 * Generates the LLVM code for this atom node.
	 *
	 * @param llvmCode the StringBuilderWrapper to append the generated LLVM code to
	 * @return the value of this node, formatted as an LLVM variable if it is not a digit
	 */
	@Override
	public String generateLLVM(StringBuilderWrapper llvmCode) {
		return value.chars().allMatch(Character::isDigit) ? value : "%" + value;
	}

	/**
	 * Generates the pseudo code for this atom node.
	 *
	 * @param pseudoCode the StringBuilderWrapper to append the generated pseudo code to
	 */
	@Override
	public void generatePseudoCode(StringBuilderWrapper pseudoCode) {
		pseudoCode.append(value);
	}
}
