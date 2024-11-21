public enum GlsVariable implements Symbol {
	PROGRAM,
	CODE,
	INSTRUCTION,
	ASSIGN,
	EXPR_ARITH,
	EXPR_ARITH_PRIME,
	PROD_ARITH,
	PROD_ARITH_PRIME,
	ATOM,
	IF,
	IFSEQ,
	COND,
	NEXT_COND,
	COND_SIMPLE,
	COMP,
	WHILE,
	OUTPUT,
	INPUT,
	;

	@Override
	public String toString() {
		return switch (this) {
			case PROGRAM -> "<Program>";
			case CODE -> "<Code>";
			case INSTRUCTION -> "<Instruction>";
			case ASSIGN -> "<Assign>";
			case EXPR_ARITH -> "<ExprArith>";
			case EXPR_ARITH_PRIME -> "<ExprArith'>";
			case PROD_ARITH -> "<ProdArith>";
			case PROD_ARITH_PRIME -> "<ProdArith'>";
			case ATOM -> "<Atom>";
			case IF -> "<If>";
			case IFSEQ -> "<Ifseq>";
			case COND -> "<Cond>";
			case NEXT_COND -> "<NextCond>";
			case COND_SIMPLE -> "<CondSimple>";
			case COMP -> "<Comp>";
			case WHILE -> "<While>";
			case OUTPUT -> "<Output>";
			case INPUT -> "<Input>";
		};
	}

	@Override
	public String toLatex() {
		return switch (this) {
			case PROGRAM -> "\\langle \\text{Program} \\rangle";
			case CODE -> "\\langle \\text{Code} \\rangle";
			case INSTRUCTION -> "\\langle \\text{Instruction} \\rangle";
			case ASSIGN -> "\\langle \\text{Assign} \\rangle";
			case EXPR_ARITH -> "\\langle \\text{ExprArith} \\rangle";
			case EXPR_ARITH_PRIME -> "\\langle \\text{ExprArith}' \\rangle";
			case PROD_ARITH -> "\\langle \\text{ProdArith} \\rangle";
			case PROD_ARITH_PRIME -> "\\langle \\text{ProdArith}' \\rangle";
			case ATOM -> "\\langle \\text{Atom} \\rangle";
			case IF -> "\\langle \\text{If} \\rangle";
			case IFSEQ -> "\\langle \\text{Ifseq} \\rangle";
			case COND -> "\\langle \\text{Cond} \\rangle";
			case NEXT_COND -> "\\langle \\text{NextCond} \\rangle";
			case COND_SIMPLE -> "\\langle \\text{CondSimple} \\rangle";
			case COMP -> "\\langle \\text{Comp} \\rangle";
			case WHILE -> "\\langle \\text{While} \\rangle";
			case OUTPUT -> "\\langle \\text{Output} \\rangle";
			case INPUT -> "\\langle \\text{Input} \\rangle";
		};
	}


}
