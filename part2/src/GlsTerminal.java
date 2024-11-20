public enum GlsTerminal implements GlsToken {
	LET,
	PROGNAME,
	BE,
	END,
	COLUMN,
	ASSIGN,
	PLUS,
	MINUS,
	MULT,
	DIV,
	NUMBER,
	VARNAME,
	LPAREN,
	RPAREN,
	LBRACK,
	RBRACK,
	IF,
	THEN,
	ELSE,
	IMPLIES,
	PIPE,
	EQUAL,
	SEQUAL,
	SMALLER,
	WHILE,
	REPEAT,
	OUT,
	IN,


	EOS,
	;

	public static GlsTerminal fromLexicalUnit(LexicalUnit type) {
		return switch (type) {
			case LexicalUnit.PROGNAME -> PROGNAME;
			case LexicalUnit.VARNAME -> VARNAME;
			case LexicalUnit.NUMBER -> NUMBER;
			case LexicalUnit.LET -> LET;
			case LexicalUnit.BE -> BE;
			case LexicalUnit.END -> END;
			case LexicalUnit.COLUMN -> COLUMN;
			case LexicalUnit.ASSIGN -> ASSIGN;
			case LexicalUnit.LPAREN -> LPAREN;
			case LexicalUnit.RPAREN -> RPAREN;
			case LexicalUnit.MINUS -> MINUS;
			case LexicalUnit.PLUS -> PLUS;
			case LexicalUnit.TIMES -> MULT;
			case LexicalUnit.DIVIDE -> DIV;
			case LexicalUnit.IF -> IF;
			case LexicalUnit.THEN -> THEN;
			case LexicalUnit.ELSE -> ELSE;
			case LexicalUnit.LBRACK -> LBRACK;
			case LexicalUnit.RBRACK -> RBRACK;
			case LexicalUnit.IMPLIES -> IMPLIES;
			case LexicalUnit.PIPE -> PIPE;
			case LexicalUnit.EQUAL -> EQUAL;
			case LexicalUnit.SMALEQ -> SEQUAL;
			case LexicalUnit.SMALLER -> SMALLER;
			case LexicalUnit.WHILE -> WHILE;
			case LexicalUnit.REPEAT -> REPEAT;
			case LexicalUnit.OUTPUT -> OUT;
			case LexicalUnit.INPUT -> IN;
			case LexicalUnit.EOS -> EOS;
		};
	}
}
