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
		boolean a = false;
		if (!VariableManager.getInstance().isDeclared(this.varName)) {
			llvmCode.appendln("%%%s = alloca i32, align 4".formatted(varName));
			VariableManager.getInstance().declare(this.varName);
			if (!VariableManager.getInstance().isDeclared(expr.generateLLVM(llvmCode))) {
				a = true;
			}
		}
		if (!(expr instanceof AtomNode)) {
			String varRes = expr.generateLLVM(llvmCode);
			llvmCode.appendln(String.format("store i32 %s, i32* %%%s, align 4", varRes, varName));
		} else {
			if (!a && VariableManager.getInstance().isDeclared(this.varName)) {
				String second = expr.generateLLVM(llvmCode);
				String var1 = llvmCode.createTempVar();
				llvmCode.appendln(String.format("%s = load i32, i32* %s, align 4", var1, second));
				llvmCode.appendln(String.format("store i32 %s, i32* %%%s, align 4", var1, varName));
				return null;
			}
			llvmCode.append("store i32 ");
			llvmCode.append(expr.generateLLVM(llvmCode));
			llvmCode.appendln(", i32* %%%s, align 4".formatted(varName));
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
