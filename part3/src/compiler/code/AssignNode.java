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
	public void generateLLVM(StringBuilder llvmCode) {
		llvmCode.append("\t%%%s = alloca i32, align 4\n".formatted(varName)); //TODO if not already declared
		llvmCode.append("\tstore i32 ");
		expr.generateLLVM(llvmCode);
		llvmCode.append(", i32* %%%s, align 4\n".formatted(varName));
		//TODO if not declared
		llvmCode.append("\t%%%s = load i32".formatted(varName+"_val"));
		llvmCode.append(", i32* %%%s, align 4\n".formatted(varName));
	}
}
