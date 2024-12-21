package compiler.code;

public class PipeCondNode implements ConditionComponent {

	private final ConditionComponent conditionNode;

	public PipeCondNode(ConditionComponent conditionNode) {
		this.conditionNode = conditionNode;
	}

	@Override
	public String generateLLVM(StringBuilderWrapper llvmCode) {
		return conditionNode.generateLLVM(llvmCode);
	}

	@Override
	public void generatePseudoCode(StringBuilderWrapper pseudoCode) {
		pseudoCode.append("(");
		conditionNode.generatePseudoCode(pseudoCode);
		pseudoCode.append(")");
	}
}
