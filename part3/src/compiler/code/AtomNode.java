package compiler.code;

import compiler.ParseTree;

public class AtomNode implements ExprComponent {
	private final String value; // Can be a variable name, a number

	public AtomNode(String value) {
		this.value = value;
	}

	@Override
	public void generateLLVM(StringBuilderWrapper llvmCode) {
		if (! value.chars().allMatch(Character::isDigit)){
			llvmCode.append("%");
			llvmCode.append(value);
			//llvmCode.append("_val");
			//llvmCode.append("i");
		}
		else llvmCode.append(value);
	}

	@Override
	public void generatePseudoCode(StringBuilderWrapper pseudoCode) {
		pseudoCode.append(value);
	}
}
