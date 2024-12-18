package compiler.code;

public class PipeCondNode implements ConditionComponent {

	private final ConditionComponent conditionNode;

	public PipeCondNode(ConditionComponent conditionNode) {
		this.conditionNode = conditionNode;
	}

	@Override
	public void generateLLVM(StringBuilder llvmCode) {
		// TODO: Implement this
	}

}
