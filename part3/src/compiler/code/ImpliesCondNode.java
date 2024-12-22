package compiler.code;

/**
 * Represents a node in the condition tree that implements the logical implication.
 */
public class ImpliesCondNode implements ConditionComponent {

	private final ConditionComponent left;
	private final ConditionComponent right;

	/**
	 * Constructs an ImpliesCondNode with the given left and right condition components.
	 *
	 * @param left  the left condition component
	 * @param right the right condition component
	 */
	public ImpliesCondNode(ConditionComponent left, ConditionComponent right) {
		this.left = left;
		this.right = right;
	}

	/**
	 * Generates the LLVM code for the implication condition.
	 *
	 * @param llvmCode the StringBuilderWrapper to append the generated LLVM code
	 * @return the variable name holding the result of the implication condition
	 */
	@Override
	public String generateLLVM(StringBuilderWrapper llvmCode) {
		String var1 = left.generateLLVM(llvmCode);
		String var2 = right.generateLLVM(llvmCode);
		String var3 = LabelManager.getInstance().createTempCond();
		String l = llvmCode.createTempVar();
		llvmCode.appendln(String.format("%s = xor i1 %%%s, true", l, var1));
		llvmCode.appendln(String.format("%%%s = or i1 %s, %%%s", var3, l, var2));
		return var3;
	}

	/**
	 * Generates the pseudo code representation of the implication condition.
	 *
	 * @param pseudoCode the StringBuilderWrapper to append the generated pseudo code
	 */
	@Override
	public void generatePseudoCode(StringBuilderWrapper pseudoCode) {
		left.generatePseudoCode(pseudoCode);
		pseudoCode.append(" -> ");
		right.generatePseudoCode(pseudoCode);
	}
}