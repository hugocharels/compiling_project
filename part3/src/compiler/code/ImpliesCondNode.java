package compiler.code;

public class ImpliesCondNode implements ConditionComponent {

	private final ConditionComponent left;
	private final ConditionComponent right;

	public ImpliesCondNode(ConditionComponent left, ConditionComponent right) {
		this.left = left;
		this.right = right;
	}

	@Override
	public void generateLLVM(StringBuilderWrapper llvmCode) {
		// TODO: Implement this
		left.generateLLVM(llvmCode);
		llvmCode.append(" -> ");
		right.generateLLVM(llvmCode);
//		llvmCode.append("\n");
	}

	@Override
	public void generateLLVM(StringBuilderWrapper llvmCode, String varName) {

	}

	@Override
	public void generatePseudoCode(StringBuilderWrapper pseudoCode) {
		left.generatePseudoCode(pseudoCode);
		pseudoCode.append(" -> ");
		right.generatePseudoCode(pseudoCode);
	}
}
