package compiler.code;

import compiler.ParseTree;

public interface ExprComponent extends CodeComponent {

	static ExprComponent fromParseTree(ParseTree parseTree) {
		// ExprArith -> [VarName] | [Number]
		//            -> ( <ExprArith> )
		//            -> - <ExprArith>
		//            -> <ExprArith> <Op> <ExprArith>

		// <ProdArith> <ExprArith'>
		//    0            1

		ParseTree prodArith = parseTree.getChild(0);
		ParseTree exprArithPrime = parseTree.getChild(1);

		// If it is a number or a variable name
//		if (
//				exprArithPrime.getChild(0).getLexicalSymbol().getType().toGlsTerminal().equals(GlsTerminal.EPSILON) &&
//				true
//		) {
//			// TODO
//		}

		return null;
	}
}
