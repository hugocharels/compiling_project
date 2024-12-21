package compiler.code;

public class ImpliesCondNode implements ConditionComponent {

	private final ConditionComponent left;
	private final ConditionComponent right;

	public ImpliesCondNode(ConditionComponent left, ConditionComponent right) {
		this.left = left;
		this.right = right;
	}

	@Override
	public String generateLLVM(StringBuilderWrapper llvmCode) {

		String var1 = left.generateLLVM(llvmCode);
		String var2 = right.generateLLVM(llvmCode);
		String var3 = LabelManager.getInstance().createTempCond();
		//llvmCode.appendln( "AAAAAAA" + var1 + " " + var2 + " " + var3);
		String l = llvmCode.createTempVar();
		llvmCode.appendln(String.format("%s = xor i1 %%%s, true", l, var1));
		llvmCode.appendln(String.format("%%%s = or i1 %s, %%%s", var3, l, var2));
		return var3;
	}

	@Override
	public void generatePseudoCode(StringBuilderWrapper pseudoCode) {
		left.generatePseudoCode(pseudoCode);
		pseudoCode.append(" -> ");
		right.generatePseudoCode(pseudoCode);
	}
}
