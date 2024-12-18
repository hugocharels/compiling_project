package compiler;
import java.util.regex.PatternSyntaxException;

/**
 *
 * Scanner class, generated by JFlex.
 * Function nextToken is the important one as it reads the file and returns the next matched toke.
 *
 */

%%// Options of the scanner

%class LexicalAnalyzer // Name
%unicode               // Use unicode
%line                  // Use line counter (yyline variable)
%column                // Use character counter by line (yycolumn variable)
%function nextToken
%type LexicalSymbol
%yylexthrow PatternSyntaxException

%eofval{
	return new LexicalSymbol(LexicalUnit.EOS, yyline, yycolumn);
%eofval}

//Extended Regular Expressions

AlphaUpperCase    = [A-Z]
AlphaLowerCase    = [a-z]
Alpha             = {AlphaUpperCase}|{AlphaLowerCase}
Numeric           = [0-9]
AlphaNumeric      = {Alpha}|{Numeric}
// LowerAlphaNumeric = {AlphaLowerCase}|{Numeric}

BadInteger     = (0[0-9]+)
Integer        = ([1-9][0-9]*)|0
ProgName       = {AlphaUpperCase}({Alpha}|"_")*
VarName        = ({AlphaLowerCase})({AlphaNumeric})*
LineFeed       = "\n"
CarriageReturn = "\r"
EndLine        = ({LineFeed}{CarriageReturn}?) | ({CarriageReturn}{LineFeed}?)
Space          = (\t | \f | " ")
Spaces         = {Space}+
Separator      = ({Spaces}) | ({EndLine}) // Was Space (no 's') was it a typo? (did produce a warning)
Any            = ([^"\n""\r"])*
UpToEnd        = ({Any}{EndLine}) | ({EndLine})

//Declare exclusive states
%xstate YYINITIAL, SHORTCOMMENTS, LONGCOMMENTS

%%// Identification of tokens


<LONGCOMMENTS> {
// End of comment
	"!!"			{yybegin(YYINITIAL);} // go back to analysis
  <<EOF>>          {throw new PatternSyntaxException("A comment is never closed.",yytext(),yyline);}
	[^]					     {} //ignore any character
}

<YYINITIAL> {
// Comments
    "!!"              {yybegin(LONGCOMMENTS);} // go to ignore mode
    "$"{UpToEnd}     {} // go to ignore mode
// Code delimiters
  "LET"             {return new LexicalSymbol(LexicalUnit.LET, yyline, yycolumn, yytext());}
  "BE"              {return new LexicalSymbol(LexicalUnit.BE, yyline, yycolumn, yytext());}
  "END"             {return new LexicalSymbol(LexicalUnit.END, yyline, yycolumn, yytext());}
  ":"               {return new LexicalSymbol(LexicalUnit.COLUMN, yyline, yycolumn, yytext());}
// Assignation
  "="                {return new LexicalSymbol(LexicalUnit.ASSIGN, yyline, yycolumn, yytext());}
// Parenthesis
  "("                 {return new LexicalSymbol(LexicalUnit.LPAREN, yyline, yycolumn, yytext());}
  ")"                 {return new LexicalSymbol(LexicalUnit.RPAREN, yyline, yycolumn, yytext());}
// Brackets
  "{"                 {return new LexicalSymbol(LexicalUnit.LBRACK, yyline, yycolumn, yytext());}
  "}"                 {return new LexicalSymbol(LexicalUnit.RBRACK, yyline, yycolumn, yytext());}
  "|"                 {return new LexicalSymbol(LexicalUnit.PIPE, yyline, yycolumn, yytext());}
// Arithmetic signs
  "+"                 {return new LexicalSymbol(LexicalUnit.PLUS, yyline, yycolumn, yytext());}
  "-"                 {return new LexicalSymbol(LexicalUnit.MINUS, yyline, yycolumn, yytext());}
  "*"                 {return new LexicalSymbol(LexicalUnit.TIMES, yyline, yycolumn, yytext());}
  "/"                 {return new LexicalSymbol(LexicalUnit.DIVIDE, yyline, yycolumn, yytext());}
// Logical operators
  "->"               {return new LexicalSymbol(LexicalUnit.IMPLIES, yyline, yycolumn, yytext());}
// Conditional keywords
  "IF"                {return new LexicalSymbol(LexicalUnit.IF, yyline, yycolumn, yytext());}
  "THEN"              {return new LexicalSymbol(LexicalUnit.THEN, yyline, yycolumn, yytext());}
  "ELSE"              {return new LexicalSymbol(LexicalUnit.ELSE, yyline, yycolumn, yytext());}
// Loop keywords
  "WHILE"             {return new LexicalSymbol(LexicalUnit.WHILE, yyline, yycolumn, yytext());}
  "REPEAT"            {return new LexicalSymbol(LexicalUnit.REPEAT, yyline, yycolumn, yytext());}
// Comparison operators
  "=="                {return new LexicalSymbol(LexicalUnit.EQUAL, yyline, yycolumn, yytext());}
  "<="                {return new LexicalSymbol(LexicalUnit.SMALEQ, yyline, yycolumn, yytext());}
  "<"                 {return new LexicalSymbol(LexicalUnit.SMALLER, yyline, yycolumn, yytext());}
// IO keywords
  "OUT"             {return new LexicalSymbol(LexicalUnit.OUTPUT, yyline, yycolumn, yytext());}
  "IN"              {return new LexicalSymbol(LexicalUnit.INPUT, yyline, yycolumn, yytext());}
// Numbers
  {BadInteger}        {System.err.println("Warning! Numbers with leading zeros are deprecated: " + yytext()); return new LexicalSymbol(LexicalUnit.NUMBER, yyline, yycolumn, Integer.valueOf(yytext()));}
  {Integer}           {return new LexicalSymbol(LexicalUnit.NUMBER, yyline, yycolumn, Integer.valueOf(yytext()));}
  {ProgName}           {return new LexicalSymbol(LexicalUnit.PROGNAME,yyline, yycolumn,yytext());}
  {VarName}           {return new LexicalSymbol(LexicalUnit.VARNAME,yyline, yycolumn,yytext());}
  {Separator}         {}// ignore spaces
  [^]                 {throw new PatternSyntaxException("Unmatched token, out of symbols",yytext(),yyline);} // unmatched token gives an error
}