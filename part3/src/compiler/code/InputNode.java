package compiler.code;

public class InputNode implements CodeComponent {

	private final String variableName;

	public InputNode(String variableName) {
		this.variableName = variableName;
	}

	@Override
	public void generateLLVM(StringBuilder llvmCode) {
		// TODO: Implement this
	}
}
