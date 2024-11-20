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
		switch (this) {
			case PROGRAM: return "<Program>";
			case CODE: return "<Code>";
			case INSTRUCTION: return "<Instruction>";
			case ASSIGN: return "<Assign>";
			case EXPR_ARITH: return "<ExprArith>";
			case EXPR_ARITH_PRIME: return "<ExprArith'>";
			case PROD_ARITH: return "<ProdArith>";
			case PROD_ARITH_PRIME: return "<ProdArith'>";
			case ATOM: return "<Atom>";
			case IF: return "<If>";
			case IFSEQ: return "<Ifseq>";
			case COND: return "<Cond>";
			case NEXT_COND: return "<NextCond>";
			case COND_SIMPLE: return "<CondSimple>";
			case COMP: return "<Comp>";
			case WHILE: return "<While>";
			case OUTPUT: return "<Output>";
			case INPUT: return "<Input>";
			default: return "";
		}
	}

}
