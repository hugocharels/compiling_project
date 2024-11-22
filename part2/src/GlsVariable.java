/**
 * Enum representing the variables in the GILLES language.
 */
public enum GlsVariable implements Symbol {
	/**
	 * The program variable.
	 */
	PROGRAM,
	/**
	 * The code variable.
	 */
	CODE,
	/**
	 * The instruction variable.
	 */
	INSTRUCTION,
	/**
	 * The assign variable.
	 */
	ASSIGN,
	/**
	 * The arithmetic expression variable.
	 */
	EXPR_ARITH,
	/**
	 * The prime arithmetic expression variable.
	 */
	EXPR_ARITH_PRIME,
	/**
	 * The arithmetic product variable.
	 */
	PROD_ARITH,
	/**
	 * The prime arithmetic product variable.
	 */
	PROD_ARITH_PRIME,
	/**
	 * The atom variable.
	 */
	ATOM,
	/**
	 * The if variable.
	 */
	IF,
	/**
	 * The if sequence variable.
	 */
	IFSEQ,
	/**
	 * The condition variable.
	 */
	COND,
	/**
	 * The next condition variable.
	 */
	NEXT_COND,
	/**
	 * The simple condition variable.
	 */
	COND_SIMPLE,
	/**
	 * The comparison variable.
	 */
	COMP,
	/**
	 * The while variable.
	 */
	WHILE,
	/**
	 * The output variable.
	 */
	OUTPUT,
	/**
	 * The input variable.
	 */
	INPUT,
	;

	/**
	 * Returns the string representation of the variable.
	 *
	 * @return the string representation of the variable
	 */
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

	/**
	 * Returns the LaTeX representation of the variable.
	 *
	 * @return the LaTeX representation of the variable
	 */
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