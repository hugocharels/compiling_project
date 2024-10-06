%% // Options of the scanner

%class LexicalAnalyzer  // Name of the scanner
%unicode                // Use Unicode encoding
%line                   // Use line counter (yyline variable)
%column                 // Use character counter by line (yycolumn variable)
%type Symbol            // Return type of the scanner is Symbol

// When the LET keyword has been scanned
%state LET
// When the ProgName has been scanned
%state PROG_NAME
// When the code starts (after LET ... BE)
%state CODE
// When the code is finished (after END)
%state END


// Extended Regular Expressions

AlphaUpperCase = [A-Z]
AlphaLowerCase = [a-z]
Alpha          = {AlphaUpperCase}|{AlphaLowerCase}
Numeric        = [0-9]
AlphaNumeric   = {Alpha}|{Numeric}

ProgName       = {AlphaUpperCase}({Alpha}|_)*
VarName        = {AlphaLowerCase}({Alpha}|{Numeric})*
Number         = {Numeric}+

Whitespace     = [ \t\r\n]+

Comment        = "$"({AlphaNumeric}|[ \t])* | "!!"({AlphaNumeric}|{Whitespace})*"!!" // should accept special character and reject $


%%// Lexical rules to match tokens

<YYINITIAL> "LET"      { yybegin(LET);       return new Symbol(LexicalUnit.LET, yyline, yycolumn, yytext()); }
<LET>       {ProgName} { yybegin(PROG_NAME); return new Symbol(LexicalUnit.PROGNAME, yyline, yycolumn, yytext()); }
<PROG_NAME> "BE"       { yybegin(CODE);      return new Symbol(LexicalUnit.BE, yyline, yycolumn, yytext()); }
<CODE>      "END"      { /*yybegin(END)*/;   return new Symbol(LexicalUnit.END, yyline, yycolumn, yytext()); }



// Whitespace (ignore)
{Whitespace}    { /* Skip whitespace */ }

// Comments
{Comment}       { /* Skip comments */ }

// End of file
<<EOF>>         { return new Symbol(LexicalUnit.EOS, yyline, yycolumn); }

// Catch-all for unrecognized characters
.               { throw new Error("Unexpected character: " + yytext()); }