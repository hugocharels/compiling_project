package compiler.code;

public class PipeCondNode implements ConditionComponent {

	private final ConditionComponent conditionNode;

	public PipeCondNode(ConditionComponent conditionNode) {
		this.conditionNode = conditionNode;
	}

	@Override
	public void generateLLVM(StringBuilderWrapper llvmCode) {
		// TODO: Implement this
		llvmCode.append("(");
		conditionNode.generateLLVM(llvmCode);
		llvmCode.append(")");
	}

	@Override
	public void generatePseudoCode(StringBuilderWrapper pseudoCode) {
		pseudoCode.append("(");
		conditionNode.generatePseudoCode(pseudoCode);
		pseudoCode.append(")");
	}
}
