package compiler.code;

import compiler.ParseTree;

public class AssignNode implements CodeComponent {
	private final String varName;
	private final ExprComponent expr;

	public AssignNode(String varName, ExprComponent expr) {
		this.varName = varName;
		this.expr = expr;
	}

	public static AssignNode fromParseTree(ParseTree parseTree) {
		return new AssignNode(parseTree.getChildren().get(0).getLexicalSymbol().getValue().toString(), ExprComponent.fromParseTree(parseTree.getChildren().get(2)));
	}

	@Override
	public String generateLLVM(StringBuilderWrapper llvmCode) {
		boolean newDirectDeclaration = false;
		String varRes = expr.generateLLVM(llvmCode);
		//new variable
		//llvmCode.append("TGM-0" + varName);
		if (!VariableManager.getInstance().isDeclared(this.varName)) {
			llvmCode.appendln("%%%s = alloca i32, align 4".formatted(varName));
			VariableManager.getInstance().declare(this.varName);
			//assign a value (not in a variable)
			//llvmCode.append("TGM0" + varRes);
			if (!VariableManager.getInstance().isDeclared(varRes)) {
				newDirectDeclaration = true;
			}
		}

		//if it is not a direct value (so an expression)
		if (!(expr instanceof AtomNode)) {
			llvmCode.appendln(String.format("store i32 %s, i32* %%%s, align 4", varRes, varName));
		} else {
			if (!varRes.chars().allMatch(Character::isDigit)) {
				//we need to access to a previous variable
				String var1 = llvmCode.createTempVar();
				//llvmCode.append("TGM");
				llvmCode.appendln(String.format("%s = load i32, i32* %s, align 4", var1, varRes));
				llvmCode.appendln(String.format("store i32 %s, i32* %%%s, align 4", var1, varName));
				return null;
			}
			llvmCode.appendln(String.format("store i32 %s, i32* %%%s, align 4", varRes, varName));
		}

		return null;
	}

	@Override
	public void generatePseudoCode(StringBuilderWrapper pseudoCode) {
		pseudoCode.append("%s = ".formatted(varName));
		expr.generatePseudoCode(pseudoCode);
		pseudoCode.appendln();
	}
}
