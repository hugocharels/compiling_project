%% // Options of the scanner

%class LexicalAnalyzer  // Name of the scanner
%unicode                // Use Unicode encoding
%line                   // Use line counter (yyline variable)
%column                 // Use character counter by line (yycolumn variable)
%type Symbol            // Return type of the scanner is Symbol

// Define a state to handle the special rule after "LET"
%state LET_STATE

// Extended Regular Expressions
AlphaUpperCase = [A-Z]
AlphaLowerCase = [a-z]
Alpha          = {AlphaUpperCase}|{AlphaLowerCase}
Numeric        = [0-9]
AlphaNumeric   = {Alpha}|{Numeric}

Sign           = [+-]
Integer        = {Sign}?(([1-9][0-9]*)|0)
Decimal        = \.[0-9]+
Exponent       = [eE]{Sign}?{Numeric}+ 
Real           = {Integer}{Decimal}?({Exponent})? 
Identifier     = {Alpha}{AlphaNumeric}*
ProgName       = {AlphaUpperCase}({Alpha}|_)*

Whitespace     = [ \t\r\n]+
//Comment        = "!!" {AlphaNumeric}|{Whitespace}* "!!" | "$" {AlphaNumeric}* "\n"


%%// Lexical rules to match tokens
// Keywords
"LET"           { yybegin(LET_STATE); return new Symbol(LexicalUnit.LET, yyline, yycolumn, "LET"); }
<LET_STATE>{ProgName} { yybegin(YYINITIAL); return new Symbol(LexicalUnit.PROGNAME, yyline, yycolumn, yytext()); }
<LET_STATE>{Identifier}    {yybegin(YYINITIAL); throw new Error("Unexpected character: " + yytext()); }

"BE"            { return new Symbol(LexicalUnit.BE, yyline, yycolumn, "BE"); }
"END"           { return new Symbol(LexicalUnit.END, yyline, yycolumn, "END"); }
"IF"            { return new Symbol(LexicalUnit.IF, yyline, yycolumn, "IF"); }
"THEN"          { return new Symbol(LexicalUnit.THEN, yyline, yycolumn, "THEN"); }
"ELSE"          { return new Symbol(LexicalUnit.ELSE, yyline, yycolumn, "ELSE"); }
"WHILE"         { return new Symbol(LexicalUnit.WHILE, yyline, yycolumn, "WHILE"); }
"REPEAT"        { return new Symbol(LexicalUnit.REPEAT, yyline, yycolumn, "REPEAT"); }
"OUT"           { return new Symbol(LexicalUnit.OUTPUT, yyline, yycolumn, "OUT"); }
"IN"            { return new Symbol(LexicalUnit.INPUT, yyline, yycolumn, "IN"); }

// Operators and Punctuation
":"             { return new Symbol(LexicalUnit.COLUMN, yyline, yycolumn, ":"); }
"="             { return new Symbol(LexicalUnit.ASSIGN, yyline, yycolumn, "="); }
"("             { return new Symbol(LexicalUnit.LPAREN, yyline, yycolumn, "("); }
")"             { return new Symbol(LexicalUnit.RPAREN, yyline, yycolumn, ")"); }
"-"             { return new Symbol(LexicalUnit.MINUS, yyline, yycolumn, "-"); }
"+"             { return new Symbol(LexicalUnit.PLUS, yyline, yycolumn, "+"); }
"*"             { return new Symbol(LexicalUnit.TIMES, yyline, yycolumn, "*"); }
"/"             { return new Symbol(LexicalUnit.DIVIDE, yyline, yycolumn, "/"); }
"{"             { return new Symbol(LexicalUnit.LBRACK, yyline, yycolumn, "{"); }
"}"             { return new Symbol(LexicalUnit.RBRACK, yyline, yycolumn, "}"); }
"->"            { return new Symbol(LexicalUnit.IMPLIES, yyline, yycolumn, "->"); }
"|"             { return new Symbol(LexicalUnit.PIPE, yyline, yycolumn, "|"); }
"=="            { return new Symbol(LexicalUnit.EQUAL, yyline, yycolumn, "=="); }
"<="            { return new Symbol(LexicalUnit.SMALEQ, yyline, yycolumn, "<="); }
"<"             { return new Symbol(LexicalUnit.SMALLER, yyline, yycolumn, "<"); }

// Identifiers and Numbers
{Identifier}    { return new Symbol(LexicalUnit.VARNAME, yyline, yycolumn, yytext()); }
{Real}          { return new Symbol(LexicalUnit.NUMBER, yyline, yycolumn, Double.parseDouble(yytext())); }
{Integer}       { return new Symbol(LexicalUnit.NUMBER, yyline, yycolumn, Integer.parseInt(yytext())); }

// Whitespace (ignore)
{Whitespace}    { /* Skip whitespace */ }
// Match comments without returning a symbol
//{Comment}      { /* Skip comment */ }

// End of file
<<EOF>>         { return new Symbol(LexicalUnit.EOS, yyline, yycolumn); }

// Catch-all for unrecognized characters
.               { throw new Error("Unexpected character: " + yytext()); }