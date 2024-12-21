package compiler.code;

public class PipeCondNode implements ConditionComponent {

	private final ConditionComponent conditionNode;

	public PipeCondNode(ConditionComponent conditionNode) {
		this.conditionNode = conditionNode;
	}

	@Override
	public String generateLLVM(StringBuilderWrapper llvmCode) {
		// TODO: Implement this
		//llvmCode.append("(");
		return conditionNode.generateLLVM(llvmCode);
		//llvmCode.append(")");
		//return null;
	}

	@Override
	public void generatePseudoCode(StringBuilderWrapper pseudoCode) {
		pseudoCode.append("(");
		conditionNode.generatePseudoCode(pseudoCode);
		pseudoCode.append(")");
	}
}
