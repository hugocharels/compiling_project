package compiler.code;

import compiler.GlsTerminal;
import compiler.GlsVariable;
import compiler.ParseTree;

public interface ExprComponent extends CodeComponent {

	static ExprComponent fromParseTree(ParseTree parseTree) {
		// <ExprArith>  -> <ProdArith> <ExprArith'>
		// <ExprArith'> -> + <ProdArith> <ExprArith'>
		//              -> - <ProdArith> <ExprArith'>
		//              -> ε
		// <ProdArith>  -> <Atom> <ProdArith'>
		// <ProdArith'> -> * <Atom> <ProdArith'>
		//              -> / <Atom> <ProdArith'>
		//              -> ε
		// <Atom>       -> [Number]
		//              -> [VarName]
		//              -> ( <ExprArith> )
		//              -> - <Atom>


		// <ProdArith> <ExprArith'>
		//    0            1

		assert parseTree.getLabel().equals(GlsVariable.EXPR_ARITH) : "Expected <ExprArith> but got " + parseTree.getLabel();

		if (parseTree.getChild(1).getChild(0).getLabel().equals(GlsTerminal.EPSILON)) {
			return fromProdArithParseTree(parseTree.getChild(0));
		} else {
			return new ExprOpNode(fromProdArithParseTree(parseTree.getChild(0)), parseTree.getChild(1).getLexicalSymbol().getValue().toString(), fromExprArithPrimeParseTree(parseTree.getChild(1)));
		}
	}

	static ExprComponent fromProdArithParseTree(ParseTree parseTree) {
		// <ProdArith>  -> <Atom> <ProdArith'>

		assert parseTree.getLabel().equals(GlsVariable.PROD_ARITH) : "Expected <ProdArith> but got " + parseTree.getLabel();
		assert !parseTree.getChild(0).getLabel().equals(GlsTerminal.EPSILON) : "Expected <ProdArith> with things but got " + parseTree.getChild(0).getLabel();

		// <Atom> <ProdArith'>
		//  0       1

		// Case: <ProdArith'> -> ε
		if (parseTree.getChild(1).getChild(0).getLabel().equals(GlsTerminal.EPSILON)) {
			return fromAtomParseTree(parseTree.getChild(0));
		}
		return new ExprOpNode(fromAtomParseTree(parseTree.getChild(0)),
				parseTree.getChild(1).getChild(0).getLexicalSymbol().getValue().toString(),
				fromProdArithPrimeParseTree(parseTree.getChild(1)));
	}

	static ExprComponent fromProdArithPrimeParseTree(ParseTree parseTree) {
		// <ProdArith'> -> * <Atom> <ProdArith'>
		//              -> / <Atom> <ProdArith'>
		//              -> ε

		// Op <Atom> <ProdArith'>
		//  0    1         2

		assert parseTree.getLabel().equals(GlsVariable.PROD_ARITH_PRIME) : "Expected <ProdArith'> but got " + parseTree.getLabel();
		assert !parseTree.getChild(0).getLabel().equals(GlsTerminal.EPSILON) : "Expected <ProdArith'> with things but got " + parseTree.getChild(0).getLabel();

		if (parseTree.getChild(2).getChild(0).getLabel().equals(GlsTerminal.EPSILON)) {
			return new ExprNode(parseTree.getChild(1).getLexicalSymbol().getValue().toString());
		}
		return new ExprOpNode(
				fromAtomParseTree(parseTree.getChild(1)),
				parseTree.getChild(2).getChild(0).getLexicalSymbol().getValue().toString(),
				fromProdArithPrimeParseTree(parseTree.getChild(2))
		);
	}

	static ExprComponent fromExprArithPrimeParseTree(ParseTree parseTree) {
		// <ExprArith'> -> + <ProdArith> <ExprArith'>
		//              -> - <ProdArith> <ExprArith'>
		//              -> ε

		assert parseTree.getLabel().equals(GlsVariable.EXPR_ARITH_PRIME) : "Expected <ExprArith'> but got " + parseTree.getLabel();
		assert !parseTree.getChild(0).getLabel().equals(GlsTerminal.EPSILON) : "Expected <ExprArith'> with things but got " + parseTree.getChild(0).getLabel();

		if (parseTree.getChild(2).getChild(0).getLabel().equals(GlsTerminal.EPSILON)) {
			return new ExprNode(parseTree.getChild(1).getLexicalSymbol().getValue().toString());
		}
		return new ExprOpNode(fromProdArithParseTree(
				parseTree.getChild(1)),
				parseTree.getChild(2).getChild(0).getLexicalSymbol().getValue().toString(),
				fromExprArithPrimeParseTree(parseTree.getChild(2))
		);
	}

	static ExprComponent fromAtomParseTree(ParseTree parseTree) {
		// <Atom>       -> [Number]
		//              -> [VarName]
		//              -> ( <ExprArith> )
		//              -> - <Atom>

		assert parseTree.getLabel().equals(GlsVariable.ATOM) : "Expected <Atom> but got " + parseTree.getLabel();

		// Case: <Atom> -> [Number] or [VarName]
		if (parseTree.getChild(0).getLabel().equals(GlsTerminal.NUMBER) || parseTree.getChild(0).getLabel().equals(GlsTerminal.VARNAME)) {
			return new ExprNode(parseTree.getChild(0).getLexicalSymbol().getValue().toString());
		}
		// Case: <Atom> -> - <Atom>
		else if (parseTree.getChild(0).getLabel().equals(GlsTerminal.MINUS)) {
			return new MinusExprNode(fromAtomParseTree(parseTree.getChild(1)));
		}
		// Case: <Atom> -> ( <ExprArith> )
		else if (parseTree.getChild(0).getLabel().equals(GlsTerminal.LPAREN)) {
			return new ParenthesisExprNode(fromParseTree(parseTree.getChild(1)));
		}
		assert false : "Invalid <Atom> parse tree";
		return null;
	}
}
