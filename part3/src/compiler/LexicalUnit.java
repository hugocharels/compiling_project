package compiler;

/**
 * A terminal symbol, a.k.a. a letter in the grammar.
 */
public enum LexicalUnit {
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
	; // End of stream

	/**
	 * Converts the LexicalUnit to its corresponding GlsTerminal.
	 *
	 * @return the corresponding GlsTerminal
	 */
	GlsTerminal toGlsTerminal() {
		return switch (this) {
			case PROGNAME -> GlsTerminal.PROGNAME;
			case VARNAME -> GlsTerminal.VARNAME;
			case NUMBER -> GlsTerminal.NUMBER;
			case LET -> GlsTerminal.LET;
			case BE -> GlsTerminal.BE;
			case END -> GlsTerminal.END;
			case COLUMN -> GlsTerminal.COLUMN;
			case ASSIGN -> GlsTerminal.ASSIGN;
			case LPAREN -> GlsTerminal.LPAREN;
			case RPAREN -> GlsTerminal.RPAREN;
			case MINUS -> GlsTerminal.MINUS;
			case PLUS -> GlsTerminal.PLUS;
			case TIMES -> GlsTerminal.TIMES;
			case DIVIDE -> GlsTerminal.DIVIDE;
			case IF -> GlsTerminal.IF;
			case THEN -> GlsTerminal.THEN;
			case ELSE -> GlsTerminal.ELSE;
			case LBRACK -> GlsTerminal.LBRACK;
			case RBRACK -> GlsTerminal.RBRACK;
			case IMPLIES -> GlsTerminal.IMPLIES;
			case PIPE -> GlsTerminal.PIPE;
			case EQUAL -> GlsTerminal.EQUAL;
			case SMALEQ -> GlsTerminal.SMALEQ;
			case SMALLER -> GlsTerminal.SMALLER;
			case WHILE -> GlsTerminal.WHILE;
			case REPEAT -> GlsTerminal.REPEAT;
			case OUTPUT -> GlsTerminal.OUTPUT;
			case INPUT -> GlsTerminal.INPUT;
			case EOS -> GlsTerminal.EOS;
		};
	}
}