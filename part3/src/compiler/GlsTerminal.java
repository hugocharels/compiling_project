package compiler;

/**
 * Enum representing the terminals in the GILLES language.
 */
public enum GlsTerminal implements Symbol {

	/**
	 * [ProgName]
	 */
	PROGNAME,
	/**
	 * [VarName]
	 */
	VARNAME,
	/**
	 * [Number]
	 */
	NUMBER,
	/**
	 * <code>LET</code>
	 */
	LET,
	/**
	 * <code>BE</code>
	 */
	BE,
	/**
	 * <code>END</code>
	 */
	END,
	/**
	 * <code>:</code>
	 */
	COLUMN,
	/**
	 * <code>=</code>
	 */
	ASSIGN,
	/**
	 * <code>(</code>
	 */
	LPAREN,
	/**
	 * <code>)</code>
	 */
	RPAREN,
	/**
	 * <code>-</code>
	 */
	MINUS,
	/**
	 * <code>+</code>
	 */
	PLUS,
	/**
	 * <code>*</code>
	 */
	TIMES,
	/**
	 * <code>/</code>
	 */
	DIVIDE,
	/**
	 * <code>IF</code>
	 */
	IF,
	/**
	 * <code>THEN</code>
	 */
	THEN,
	/**
	 * <code>ELSE</code>
	 */
	ELSE,
	/**
	 * <code>{</code>
	 */
	LBRACK,
	/**
	 * <code>}</code>
	 */
	RBRACK,
	/**
	 * <code>-></code>
	 */
	IMPLIES,
	/**
	 * <code>|</code>
	 */
	PIPE,
	/**
	 * <code>==</code>
	 */
	EQUAL,
	/**
	 * <code>&lt;=</code>
	 */
	SMALEQ,
	/**
	 * <code>&lt;</code>
	 */
	SMALLER,
	/**
	 * <code>WHILE</code>
	 */
	WHILE,
	/**
	 * <code>REPEAT</code>
	 */
	REPEAT,
	/**
	 * <code>FOR</code>
	 */
	FOR,
	/**
	 * <code>FROM</code>
	 */
	FROM,
	/**
	 * <code>TO</code>
	 */
	TO,
	/**
	 * <code>STEP</code>
	 */
	STEP,
	/**
	 * <code>OUT</code>
	 */
	OUTPUT,
	/**
	 * <code>IN</code>
	 */
	INPUT,
	/**
	 * End Of Stream
	 */
	EOS,

	/**
	 * Epsilon (empty string)
	 */
	EPSILON,

	; // End of stream


	/**
	 * Converts the terminal to its LaTeX representation.
	 *
	 * @return the LaTeX representation of the terminal
	 */
	@Override
	public String toLatex() {
		return switch (this) {
			case PROGNAME -> "[\\texttt{ProgName}]";
			case VARNAME -> "[\\texttt{VarName}]";
			case NUMBER -> "[\\texttt{Number}]";
			case LET -> "\\texttt{LET}";
			case BE -> "\\texttt{BE}";
			case END -> "\\texttt{END}";
			case COLUMN -> "\\texttt{:}";
			case ASSIGN -> "\\texttt{=}";
			case LPAREN -> "\\texttt{(}";
			case RPAREN -> "\\texttt{)}";
			case MINUS -> "\\texttt{-}";
			case PLUS -> "\\texttt{+}";
			case TIMES -> "\\texttt{*}";
			case DIVIDE -> "\\texttt{/}";
			case IF -> "\\texttt{IF}";
			case THEN -> "\\texttt{THEN}";
			case ELSE -> "\\texttt{ELSE}";
			case LBRACK -> "\\texttt{\\{}";
			case RBRACK -> "\\texttt{\\}}";
			case IMPLIES -> "\\texttt{->}";
			case PIPE -> "\\texttt{|}";
			case EQUAL -> "\\texttt{==}";
			case SMALEQ -> "\\texttt{<=}";
			case SMALLER -> "\\texttt{<}";
			case WHILE -> "\\texttt{WHILE}";
			case REPEAT -> "\\texttt{REPEAT}";
			case FOR -> "\\texttt{FOR}";
			case FROM -> "\\texttt{FROM}";
			case TO -> "\\texttt{TO}";
			case STEP -> "\\texttt{STEP}";
			case OUTPUT -> "\\texttt{OUT}";
			case INPUT -> "\\texttt{IN}";
			case EOS -> "\\texttt{EOS}";
			case EPSILON -> "\\varepsilon";
		};
	}

	public String toString() {
		return switch (this) {
			case PROGNAME -> "[ProgName]";
			case VARNAME -> "[VarName]";
			case NUMBER -> "[Number]";
			case LET -> "LET";
			case BE -> "BE";
			case END -> "END";
			case COLUMN -> ":";
			case ASSIGN -> "=";
			case LPAREN -> "(";
			case RPAREN -> ")";
			case MINUS -> "-";
			case PLUS -> "+";
			case TIMES -> "*";
			case DIVIDE -> "/";
			case IF -> "IF";
			case THEN -> "THEN";
			case ELSE -> "ELSE";
			case LBRACK -> "{";
			case RBRACK -> "}";
			case IMPLIES -> "->";
			case PIPE -> "|";
			case EQUAL -> "==";
			case SMALEQ -> "<=";
			case SMALLER -> "<";
			case WHILE -> "WHILE";
			case REPEAT -> "REPEAT";
			case FOR -> "FOR";
			case FROM -> "FROM";
			case TO -> "TO";
			case STEP -> "STEP";
			case OUTPUT -> "OUT";
			case INPUT -> "IN";
			case EOS -> "EOS";
			case EPSILON -> "ε";
		};
	}
}