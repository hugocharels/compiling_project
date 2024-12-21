package compiler.code;

public class AtomNode implements ExprComponent {
	private final String value; // Can be a variable name, a number

	public AtomNode(String value) {
		this.value = value;
	}

	@Override
	public String generateLLVM(StringBuilderWrapper llvmCode) {
		return value.chars().allMatch(Character::isDigit) ? value : "%" + value;
	}

	@Override
	public void generatePseudoCode(StringBuilderWrapper pseudoCode) {
		pseudoCode.append(value);
	}
}
