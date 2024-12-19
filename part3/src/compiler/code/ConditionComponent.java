package compiler.code;

import compiler.GlsTerminal;
import compiler.GlsVariable;
import compiler.ParseTree;

public interface ConditionComponent extends CodeComponent {

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


	static ConditionComponent fromNextCondParseTree(ParseTree parseTree) {
		// <NextCond> -> -> <CondSimple> <NextCond>
		//            -> ε

		// NextCond -> ε
		if (parseTree.getChild(0).getLabel().equals(GlsTerminal.EPSILON)) {
			return null;
		}

		// NextCond -> -> <CondSimple> <NextCond>
		ParseTree newParseTree = new ParseTree(GlsVariable.COND);
		newParseTree.addChild(parseTree.getChild(1));
		newParseTree.addChild(parseTree.getChild(2));
		return fromParseTree(newParseTree);
	}
}
