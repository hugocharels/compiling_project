package code;

public class WhileNode implements CodeComponent {
	private final ConditionComponent condition;
	private final BlockNode body;

	public WhileNode(ConditionComponent condition, BlockNode body) {
		this.condition = condition;
		this.body = body;
	}

	@Override
	public void generateLLVM(StringBuilder llvmCode) {
		// TODO: Implement this
	}

}
