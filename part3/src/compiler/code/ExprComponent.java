package compiler.code;

import compiler.GlsTerminal;
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

		ExprComponent prodArith = fromProdArithParseTree(parseTree.getChild(0));
		ExprComponent exprArithPrime = fromExprArithPrimeParseTree(parseTree.getChild(1));

		if (exprArithPrime == null) {
			return prodArith;
		} else {
			return new ExprOpNode(prodArith, parseTree.getChild(1).getLexicalSymbol().getValue().toString(), exprArithPrime);
		}
	}

	static ExprComponent fromProdArithParseTree(ParseTree parseTree) {
		// <ProdArith>  -> <Atom> <ProdArith'>

		if (parseTree.getChild(0).getLabel().equals(GlsTerminal.EPSILON)) {
			return null;
		}

		// Case: <ProdArith'> -> ε
		if (parseTree.getChild(1).getChild(0).getLabel().equals(GlsTerminal.EPSILON)) {
			return fromAtomParseTree(parseTree.getChild(0));
		}
		// Case: <ProdArith'> -> Op <Atom> <ProdArith'>
		return new ExprOpNode(fromAtomParseTree(parseTree.getChild(0)), parseTree.getChild(1).getChild(0).getLexicalSymbol().getValue().toString(), fromProdArithPrimeParseTree(parseTree.getChild(1).getChild(1)));
	}

	static ExprComponent fromProdArithPrimeParseTree(ParseTree parseTree) {
		// <ProdArith'> -> * <Atom> <ProdArith'>
		//              -> / <Atom> <ProdArith'>
		//              -> ε
		if (parseTree.getChild(2).getChild(0).getLabel().equals(GlsTerminal.EPSILON)) {
			return new ExprNode(parseTree.getChild(1).getLexicalSymbol().getValue().toString());
		}
		return new ExprOpNode(fromAtomParseTree(parseTree.getChild(1)), parseTree.getChild(2).getChild(0).getLexicalSymbol().getValue().toString(), fromProdArithPrimeParseTree(parseTree.getChild(2)));
	}

	static ExprComponent fromExprArithPrimeParseTree(ParseTree parseTree) {
		// <ExprArith'> -> + <ProdArith> <ExprArith'>
		//              -> - <ProdArith> <ExprArith'>
		//              -> ε

		if (parseTree.getChild(0).getLabel().equals(GlsTerminal.EPSILON)) {
			return null;
		}

		if (parseTree.getChild(2).getChild(0).getLabel().equals(GlsTerminal.EPSILON)) {
			return new ExprNode(parseTree.getChild(1).getLexicalSymbol().getValue().toString());
		}
		return new ExprOpNode(fromProdArithParseTree(parseTree.getChild(1)), parseTree.getChild(2).getChild(0).getLexicalSymbol().getValue().toString(), fromExprArithPrimeParseTree(parseTree.getChild(2)));
	}

	static ExprComponent fromAtomParseTree(ParseTree parseTree) {
		// <Atom>       -> [Number]
		//              -> [VarName]
		//              -> ( <ExprArith> )
		//              -> - <Atom>

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
			return fromParseTree(parseTree.getChild(1));
		}
		return null;
	}
}
