package compiler.code;

public class WhileNode implements CodeComponent {
	private final ConditionComponent condition;
	private final CodeBlockNode body;

	public WhileNode(ConditionComponent condition, CodeBlockNode body) {
		this.condition = condition;
		this.body = body;
	}

	@Override
	public void generateLLVM(StringBuilder llvmCode) {
		// TODO: Implement this
	}

}
