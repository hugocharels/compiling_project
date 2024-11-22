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
			case OUTPUT -> "\\texttt{OUT}";
			case INPUT -> "\\texttt{IN}";
			case EOS -> "\\texttt{EOS}";
			case EPSILON -> "\\varepsilon";
		};
	}
}