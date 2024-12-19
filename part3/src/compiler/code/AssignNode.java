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
		// TODO: assign the value given by the expression to the variable
		llvmCode.append("\t").append(this.varName).append(" = ");
		expr.generateLLVM(llvmCode);
		llvmCode.append("\n");
	}
}
