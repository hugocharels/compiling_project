%% // Options of the scanner

%class LexicalAnalyzer  // Name of the scanner
%unicode                // Use Unicode encoding
%line                   // Use line counter (yyline variable)
%column                 // Use character counter by line (yycolumn variable)
%type Symbol            // Return type of the scanner is Symbol

%xstate SIMPLE_COMMENT, BIG_COMMENT

AlphaUpperCase = [A-Z]
AlphaLowerCase = [a-z]
Alpha          = {AlphaUpperCase}|{AlphaLowerCase}
Numeric        = [0-9]
AlphaNumeric   = {Alpha}|{Numeric}

ProgName       = {AlphaUpperCase}({Alpha}|_)*
VarName        = {AlphaLowerCase}({Alpha}|{Numeric})*
Number         = {Numeric}+

Whitespace     = [ \t\r\n]+



%%// Lexical rules to match tokens

// Enter comment
"$"             { yybegin(SIMPLE_COMMENT); }
"!!"            { yybegin(BIG_COMMENT); }

// Keywords
"LET"           { return new Symbol(LexicalUnit.LET, yyline, yycolumn, yytext()); }
"BE"            { return new Symbol(LexicalUnit.BE, yyline, yycolumn, yytext()); }
"END"           { return new Symbol(LexicalUnit.END, yyline, yycolumn, yytext()); }
"IF"            { return new Symbol(LexicalUnit.IF, yyline, yycolumn, yytext()); }
"THEN"          { return new Symbol(LexicalUnit.THEN, yyline, yycolumn, yytext()); }
"ELSE"          { return new Symbol(LexicalUnit.ELSE, yyline, yycolumn, yytext()); }
"WHILE"         { return new Symbol(LexicalUnit.WHILE, yyline, yycolumn, yytext()); }
"REPEAT"        { return new Symbol(LexicalUnit.REPEAT, yyline, yycolumn, yytext()); }
"OUT"           { return new Symbol(LexicalUnit.OUTPUT, yyline, yycolumn, yytext()); }
"IN"            { return new Symbol(LexicalUnit.INPUT, yyline, yycolumn, yytext()); }

// Operators and Punctuation
":"             { return new Symbol(LexicalUnit.COLUMN, yyline, yycolumn, yytext()); }
"="             { return new Symbol(LexicalUnit.ASSIGN, yyline, yycolumn, yytext()); }
"("             { return new Symbol(LexicalUnit.LPAREN, yyline, yycolumn, yytext()); }
")"             { return new Symbol(LexicalUnit.RPAREN, yyline, yycolumn, yytext()); }
"-"             { return new Symbol(LexicalUnit.MINUS, yyline, yycolumn, yytext()); }
"+"             { return new Symbol(LexicalUnit.PLUS, yyline, yycolumn, yytext()); }
"*"             { return new Symbol(LexicalUnit.TIMES, yyline, yycolumn, yytext()); }
"/"             { return new Symbol(LexicalUnit.DIVIDE, yyline, yycolumn, yytext()); }
"{"             { return new Symbol(LexicalUnit.LBRACK, yyline, yycolumn, yytext()); }
"}"             { return new Symbol(LexicalUnit.RBRACK, yyline, yycolumn, yytext()); }
"->"            { return new Symbol(LexicalUnit.IMPLIES, yyline, yycolumn, yytext()); }
"|"             { return new Symbol(LexicalUnit.PIPE, yyline, yycolumn, yytext()); }
"=="            { return new Symbol(LexicalUnit.EQUAL, yyline, yycolumn, yytext()); }
"<="            { return new Symbol(LexicalUnit.SMALEQ, yyline, yycolumn, yytext()); }
"<"             { return new Symbol(LexicalUnit.SMALLER, yyline, yycolumn, yytext()); }

// Regex
{ProgName}      { return new Symbol(LexicalUnit.PROGNAME, yyline, yycolumn, yytext()); }
{VarName}       { return new Symbol(LexicalUnit.VARNAME, yyline, yycolumn, yytext()); }
{Number}        { return new Symbol(LexicalUnit.NUMBER, yyline, yycolumn, yytext()); }


// Comments
<SIMPLE_COMMENT> { 
	"\n"        { yybegin(YYINITIAL); }
	.           { /* Skip */ }
 }

<BIG_COMMENT> {
	"!!"        { yybegin(YYINITIAL); }
	"$"         { throw new Error("Nested comments are not allowed"); }
	.           { /* Skip */ }
}

// Whitespace (ignore)
{Whitespace}    { /* Skip whitespace */ }


// End of file
<<EOF>>         { return new Symbol(LexicalUnit.EOS, yyline, yycolumn); }

// Catch-all for unrecognized characters
.               { throw new Error("Unexpected character: " + yytext()); }
