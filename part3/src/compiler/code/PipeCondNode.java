package compiler.code;

/**
 * Represents a node in the condition component that wraps another condition component.
 */
public class PipeCondNode implements ConditionComponent {

	private final ConditionComponent conditionNode;

	/**
	 * Constructs a PipeCondNode with the specified condition component.
	 *
	 * @param conditionNode the condition component to be wrapped
	 */
	public PipeCondNode(ConditionComponent conditionNode) {
		this.conditionNode = conditionNode;
	}

	/**
	 * Generates the LLVM code for the condition component.
	 *
	 * @param llvmCode the StringBuilderWrapper to append the LLVM code to
	 * @return the generated LLVM code as a String
	 */
	@Override
	public String generateLLVM(StringBuilderWrapper llvmCode) {
		return conditionNode.generateLLVM(llvmCode);
	}

	/**
	 * Generates the pseudo code for the condition component.
	 *
	 * @param pseudoCode the StringBuilderWrapper to append the pseudo code to
	 */
	@Override
	public void generatePseudoCode(StringBuilderWrapper pseudoCode) {
		pseudoCode.append("(");
		conditionNode.generatePseudoCode(pseudoCode);
		pseudoCode.append(")");
	}
}
