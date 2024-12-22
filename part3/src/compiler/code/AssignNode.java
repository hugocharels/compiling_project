package compiler.code;

import compiler.ParseTree;

/**
 * The AssignNode class represents a node in the code that assigns a value to a variable.
 * It implements the CodeComponent interface.
 */
public class AssignNode implements CodeComponent {
	private final String varName; // The name of the variable
	private final ExprComponent expr; // The expression to be assigned to the variable

	/**
	 * Constructs an AssignNode with the specified variable name and expression.
	 *
	 * @param varName the name of the variable
	 * @param expr    the expression to be assigned to the variable
	 */
	public AssignNode(String varName, ExprComponent expr) {
		this.varName = varName;
		this.expr = expr;
	}

	/**
	 * Creates an AssignNode from a parse tree.
	 *
	 * @param parseTree the parse tree to create the AssignNode from
	 * @return the created AssignNode
	 */
	public static AssignNode fromParseTree(ParseTree parseTree) {
		return new AssignNode(parseTree.getChildren().get(0).getLexicalSymbol().getValue().toString(), ExprComponent.fromParseTree(parseTree.getChildren().get(2)));
	}

	/**
	 * Generates the LLVM code for this assignment node.
	 *
	 * @param llvmCode the StringBuilderWrapper to append the generated LLVM code to
	 * @return null
	 */
	@Override
	public String generateLLVM(StringBuilderWrapper llvmCode) {
		String varRes = expr.generateLLVM(llvmCode);

		// new variable
		if (!VariableManager.getInstance().isDeclared(this.varName)) {
			llvmCode.appendln("%%%s = alloca i32, align 4".formatted(varName));
			VariableManager.getInstance().declare(this.varName);
		}

		// if it is not a direct value (so an expression)
		if (!(expr instanceof AtomNode)) {
			llvmCode.appendln(String.format("store i32 %s, i32* %%%s, align 4", varRes, varName));
		} else {
			if (!varRes.chars().allMatch(Character::isDigit)) {
				// we need to access to a previous variable
				String var1 = llvmCode.createTempVar();
				llvmCode.appendln(String.format("%s = load i32, i32* %s, align 4", var1, varRes));
				llvmCode.appendln(String.format("store i32 %s, i32* %%%s, align 4", var1, varName));
				return null;
			}
			llvmCode.appendln(String.format("store i32 %s, i32* %%%s, align 4", varRes, varName));
		}

		return null;
	}

	/**
	 * Generates the pseudo code for this assignment node.
	 *
	 * @param pseudoCode the StringBuilderWrapper to append the generated pseudo code to
	 */
	@Override
	public void generatePseudoCode(StringBuilderWrapper pseudoCode) {
		pseudoCode.append("%s = ".formatted(varName));
		expr.generatePseudoCode(pseudoCode);
		pseudoCode.appendln();
	}
}