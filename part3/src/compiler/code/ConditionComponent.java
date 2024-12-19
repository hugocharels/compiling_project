package compiler.code;

import compiler.GlsTerminal;
import compiler.ParseTree;

public interface ConditionComponent extends CodeComponent {

	static ConditionComponent fromParseTree(ParseTree parseTree) {
		//           <Cond>
		//          /     \
		// <CondSimple>   <NextCond>

		// <CondSimple> -> | <Cond> |
		//              -> <Expr> <Comp> <Expr>

		// <NextCond> -> -> <CondSimple> <NextCond>
		//            -> ε

		ParseTree condSimple = parseTree.getChild(0);
		ParseTree nextCond = parseTree.getChild(1);

		ConditionComponent leftCond;

		// CondSimple -> | <Cond> |
		if (condSimple.getChild(0).getLabel().equals(GlsTerminal.PIPE)) {
			// | <Cond> |
			// 0   1    2
			leftCond = new PipeCondNode(ConditionComponent.fromParseTree(condSimple.getChild(1)));
			// CondSimple -> <Expr> <Comp> <Expr>
		} else {
			// <Expr> <Comp> <Expr>
			//   0      1      2
			ExprComponent left = ExprComponent.fromParseTree(condSimple.getChild(0));
			String op = condSimple.getChild(1).getLexicalSymbol().getValue().toString();
			ExprComponent right = ExprComponent.fromParseTree(condSimple.getChild(2));
			leftCond = new ExprCondNode(left, op, right);
		}

		// NextCond -> ε
		if (nextCond.getChild(0).getLabel().equals(GlsTerminal.EPSILON)) {
			return leftCond;
		}

		// NextCond -> -> <CondSimple> <NextCond>
		// TODO: Implement this case
		return null;
	}
}
