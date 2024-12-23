package compiler.code;

import compiler.GlsTerminal;
import compiler.GlsVariable;
import compiler.ParseTree;

/**
 * Represents a condition in the GLS language.
 */
public interface ConditionComponent extends CodeComponent {

	/**
	 * Creates a ConditionComponent from a parse tree.
	 *
	 * @param parseTree the parse tree to create the ConditionComponent from
	 * @return the created ConditionComponent
	 */
	static ConditionComponent fromParseTree(ParseTree parseTree) {
		//           <Cond>
		//          /     \
		// <CondSimple>   <NextCond>

		ParseTree condSimple = parseTree.getChild(0);
		ParseTree nextCond = parseTree.getChild(1);

		ConditionComponent leftCond = fromCondSimpleParseTree(condSimple);
		ConditionComponent rightCond = fromNextCondParseTree(nextCond);

		if (rightCond == null) {
			return leftCond;
		} else {
			return new ImpliesCondNode(leftCond, rightCond);
		}
	}

	/**
	 * Creates a ConditionComponent from a simple condition parse tree.
	 *
	 * @param parseTree the parse tree to create the ConditionComponent from
	 * @return the created ConditionComponent
	 */
	static ConditionComponent fromCondSimpleParseTree(ParseTree parseTree) {
		// <CondSimple> -> | <Cond> |
		//              -> <Expr> <Comp> <Expr>

		// CondSimple -> | <Cond> |
		if (parseTree.getChild(0).getLabel().equals(GlsTerminal.PIPE)) {
			// | <Cond> |
			// 0   1    2
			return new PipeCondNode(ConditionComponent.fromParseTree(parseTree.getChild(1)));

			// CondSimple -> <Expr> <Comp> <Expr>
		} else {
			// <Expr> <Comp> <Expr>
			//   0      1      2
			ExprComponent left = ExprComponent.fromParseTree(parseTree.getChild(0));
			String op = parseTree.getChild(1).getLexicalSymbol().getValue().toString();
			ExprComponent right = ExprComponent.fromParseTree(parseTree.getChild(2));
			return new ExprCondNode(left, op, right);
		}
	}

	/**
	 * Creates a ConditionComponent from a next condition parse tree.
	 *
	 * @param parseTree the parse tree to create the ConditionComponent from
	 * @return the created ConditionComponent, or null if the parse tree represents an epsilon
	 */
	static ConditionComponent fromNextCondParseTree(ParseTree parseTree) {
		// <NextCond> -> -> <CondSimple> <NextCond>
		//            -> ε

		// NextCond -> ε
		if (parseTree.getChild(0).getLabel().equals(GlsTerminal.EPSILON)) {
			return null;
		}

		// ⟨Cond⟩ → ⟨CondSimple⟩⟨NextCond⟩
		// ⟨NextCond⟩ → -> <CondSimple> <NextCond>
		// We recreate a cond parseTree (since it is equivalent) to simplify
		ParseTree newParseTree = new ParseTree(GlsVariable.COND);
		newParseTree.addChild(parseTree.getChild(1));
		newParseTree.addChild(parseTree.getChild(2));
		return fromParseTree(newParseTree);
	}

	/**
	 * Gets the LLVM logical operator corresponding to the given operator.
	 *
	 * @param op the operator
	 * @return the corresponding LLVM logical operator
	 */
	default String getLLVMLogicalOperator(String op) {
		return switch (op) {
			case "<" -> "slt";
			case "<=" -> "sle";
			case ">" -> "sgt";
			case "==" -> "eq";
			default -> "";
		};
	}
}
