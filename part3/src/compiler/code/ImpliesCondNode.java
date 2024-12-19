package compiler.code;

public class ImpliesCondNode implements ConditionComponent {

	private final ConditionComponent left;
	private final ConditionComponent right;

	public ImpliesCondNode(ConditionComponent left, ConditionComponent right) {
		this.left = left;
		this.right = right;
	}

	@Override
	public void generateLLVM(StringBuilder llvmCode) {
		// TODO: Implement this
		left.generateLLVM(llvmCode);
		llvmCode.append(" -> ");
		right.generateLLVM(llvmCode);
//		llvmCode.append("\n");
	}
}
