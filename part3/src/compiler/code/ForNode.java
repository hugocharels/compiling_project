package compiler.code;

import compiler.ParseTree;

public class ForNode implements CodeComponent {
	private final String varName;
	private final ExprComponent from;
	private final ExprComponent to;
	private final ExprComponent step;
	private final CodeBlockNode body;

	public ForNode(String varName, ExprComponent from, ExprComponent to, ExprComponent step, CodeBlockNode body) {
		this.varName = varName;
		this.from = from;
		this.to = to;
		this.step = step;
		this.body = body;
	}

	public static ForNode fromParseTree(ParseTree parseTree) {
		// FOR { [VarName] FROM <Expr> TO <Expr> STEP <Expr> } REPEAT <Code> END
		//  0  1     2      3    4     5    6      7   8     9   10    11    12
		return new ForNode(
				parseTree.getChild(2).getLexicalSymbol().getValue().toString(),
				ExprComponent.fromParseTree(parseTree.getChild(4)),
				ExprComponent.fromParseTree(parseTree.getChild(6)),
				ExprComponent.fromParseTree(parseTree.getChild(8)),
				CodeBlockNode.fromParseTree(parseTree.getChild(11))
		);
	}

	@Override
	public String generateLLVM(StringBuilderWrapper llvmCode) {
		// Use the assign and while node to generate the for loop
		// First, assign the from value to the variable
		AssignNode assignNode = new AssignNode(varName, from);
		assignNode.generateLLVM(llvmCode);
		// Create the while node
		WhileNode whileNode = new WhileNode(
				new ExprCondNode(new AtomNode(varName), "<", to),
				new CodeBlockNode(body, new CodeBlockNode(new AssignNode(varName, new ExprOpNode(new AtomNode(varName), "+", step)), null))
		);
		whileNode.generateLLVM(llvmCode);
		return null;
	}

	@Override
	public void generatePseudoCode(StringBuilderWrapper pseudoCode) {
		pseudoCode.append("for (");
		pseudoCode.append(varName);
		pseudoCode.append(" from ");
		from.generatePseudoCode(pseudoCode);
		pseudoCode.append(" to ");
		to.generatePseudoCode(pseudoCode);
		pseudoCode.append(" step ");
		step.generatePseudoCode(pseudoCode);
		pseudoCode.appendln(") {");
		pseudoCode.incrementIndentLevel();
		body.generatePseudoCode(pseudoCode);
		pseudoCode.decrementIndentLevel();
		pseudoCode.appendln("}");
	}
}
