package compiler.code;

public class AtomNode implements ExprComponent {
	private final String value; // Can be a variable name, a number

	public AtomNode(String value) {
		this.value = value;
	}

	@Override
	public String generateLLVM(StringBuilderWrapper llvmCode) {
		if (! value.chars().allMatch(Character::isDigit)){
			//llvmCode.append("%");
			//llvmCode.append(value);
			//llvmCode.append("_val");
			//llvmCode.append("i");
			return "%"+value;
		}
		else {
			//llvmCode.append(value);
			return value;
		}
	}

	@Override
	public void generatePseudoCode(StringBuilderWrapper pseudoCode) {
		pseudoCode.append(value);
	}
}
