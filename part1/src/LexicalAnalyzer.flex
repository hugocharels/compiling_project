%% // Scanner options
%class LexicalAnalyzer  
%unicode                
%line                   
%column                 
%type Symbol            

// Comment states
%xstate SIMPLE_COMMENT BIG_COMMENT

// Definitions
AlphaUpperCase = [A-Z]
AlphaLowerCase = [a-z]
Alpha          = {AlphaUpperCase}|{AlphaLowerCase}
Numeric        = [0-9]
AlphaNumeric   = {Alpha}|{Numeric}

ProgName       = {AlphaUpperCase}({Alpha}|_)*  // Program name starts with uppercase
VarName        = {AlphaLowerCase}({AlphaNumeric})*  // Variable name starts with lowercase
Number         = {Numeric}+  // Number is a sequence of digits
Whitespace     = [ \t\r\n]+  // Whitespace characters

%% // Lexical rules

// Entering comment states
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

// Operators and punctuation
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

// Regular expressions for program and variable names, numbers
{ProgName}      { return new Symbol(LexicalUnit.PROGNAME, yyline, yycolumn, yytext()); }
{VarName}       { return new Symbol(LexicalUnit.VARNAME, yyline, yycolumn, yytext()); }
{Number}        { return new Symbol(LexicalUnit.NUMBER, yyline, yycolumn, yytext()); }

// Comment states
<SIMPLE_COMMENT> {
    "\n"        { yybegin(YYINITIAL); }  // End of simple comment
    .           { /* Skip */ }           // Skip characters within the comment
	[ \t\r]     { /* Skip */ }
}

<BIG_COMMENT> {
    "!!"         { yybegin(YYINITIAL); }  // End of big comment
    .            { /* Skip */ }
    {Whitespace} { /* Skip whitespace */ }
    <<EOF>>      { throw new Error("Unclosed big comment"); } 
}

// Ignore whitespace
{Whitespace}    { /* Skip whitespace */ }

// End of file
<<EOF>>         { return new Symbol(LexicalUnit.EOS, yyline, yycolumn); }

// Catch-all for unrecognized characters
.               { throw new Error("Unexpected character: " + yytext()); }
