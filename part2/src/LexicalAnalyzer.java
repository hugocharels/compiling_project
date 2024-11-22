// DO NOT EDIT
// Generated by JFlex 1.9.1 http://jflex.de/
// source: src/LexicalAnalyzer.flex


import java.util.regex.PatternSyntaxException;

/**
 *
 * Scanner class, generated by JFlex.
 * Function nextToken is the important one as it reads the file and returns the next matched toke.
 *
 */


@SuppressWarnings("fallthrough")
class LexicalAnalyzer {

  /** This character denotes the end of file. */
  public static final int YYEOF = -1;

  /** Initial size of the lookahead buffer. */
  private static final int ZZ_BUFFERSIZE = 16384;

  // Lexical states.
  public static final int YYINITIAL = 0;
  public static final int SHORTCOMMENTS = 2;
  public static final int LONGCOMMENTS = 4;

  /**
   * ZZ_LEXSTATE[l] is the state in the DFA for the lexical state l
   * ZZ_LEXSTATE[l+1] is the state in the DFA for the lexical state l
   *                  at the beginning of a line
   * l is of the form l = 2*k, k a non negative integer
   */
  private static final int ZZ_LEXSTATE[] = {
     0,  0,  1,  1,  2, 2
  };

  /**
   * Top-level table for translating characters to character classes
   */
  private static final int [] ZZ_CMAP_TOP = zzUnpackcmap_top();

  private static final String ZZ_CMAP_TOP_PACKED_0 =
    "\1\0\u10ff\u0100";

  private static int [] zzUnpackcmap_top() {
    int [] result = new int[4352];
    int offset = 0;
    offset = zzUnpackcmap_top(ZZ_CMAP_TOP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackcmap_top(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /**
   * Second-level tables for translating characters to character classes
   */
  private static final int [] ZZ_CMAP_BLOCKS = zzUnpackcmap_blocks();

  private static final String ZZ_CMAP_BLOCKS_PACKED_0 =
    "\11\0\1\1\1\2\1\0\1\1\1\3\22\0\1\1"+
    "\1\4\2\0\1\5\3\0\1\6\1\7\1\10\1\11"+
    "\1\0\1\12\1\0\1\13\1\14\11\15\1\16\1\0"+
    "\1\17\1\20\1\21\2\0\1\22\1\23\1\24\1\25"+
    "\1\26\1\27\1\24\1\30\1\31\2\24\1\32\1\24"+
    "\1\33\1\34\1\35\1\24\1\36\1\37\1\40\1\41"+
    "\1\24\1\42\3\24\4\0\1\43\1\0\32\44\1\45"+
    "\1\46\1\47\u0182\0";

  private static int [] zzUnpackcmap_blocks() {
    int [] result = new int[512];
    int offset = 0;
    offset = zzUnpackcmap_blocks(ZZ_CMAP_BLOCKS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackcmap_blocks(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /**
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\3\0\1\1\3\2\2\1\1\3\1\4\1\5\1\6"+
    "\1\7\1\10\2\11\1\12\1\13\1\14\11\15\1\16"+
    "\1\17\1\20\1\21\2\2\1\22\1\0\1\23\1\24"+
    "\1\25\1\26\1\27\2\15\1\30\1\31\5\15\1\32"+
    "\1\15\1\33\1\34\1\35\3\15\1\36\1\15\1\37"+
    "\2\15\1\40\1\41";

  private static int [] zzUnpackAction() {
    int [] result = new int[66];
    int offset = 0;
    offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAction(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /**
   * Translates a state to a row index in the transition table
   */
  private static final int [] ZZ_ROWMAP = zzUnpackRowMap();

  private static final String ZZ_ROWMAP_PACKED_0 =
    "\0\0\0\50\0\120\0\50\0\170\0\240\0\310\0\360"+
    "\0\u0118\0\50\0\50\0\50\0\50\0\u0140\0\50\0\u0168"+
    "\0\u0190\0\50\0\u01b8\0\u01e0\0\u0208\0\u0230\0\u0258\0\u0280"+
    "\0\u02a8\0\u02d0\0\u02f8\0\u0320\0\u0348\0\u0370\0\50\0\50"+
    "\0\50\0\50\0\u0398\0\50\0\u0118\0\50\0\u0168\0\50"+
    "\0\50\0\u0208\0\u03c0\0\u03e8\0\u0208\0\u0208\0\u0410\0\u0438"+
    "\0\u0460\0\u0488\0\u04b0\0\50\0\u04d8\0\u0208\0\u0208\0\u0208"+
    "\0\u0500\0\u0528\0\u0550\0\u0208\0\u0578\0\u0208\0\u05a0\0\u05c8"+
    "\0\u0208\0\u0208";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[66];
    int offset = 0;
    offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackRowMap(String packed, int offset, int [] result) {
    int i = 0;  /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length() - 1;
    while (i < l) {
      int high = packed.charAt(i++) << 16;
      result[j++] = high | packed.charAt(i++);
    }
    return j;
  }

  /**
   * The transition table of the DFA
   */
  private static final int [] ZZ_TRANS = zzUnpacktrans();

  private static final String ZZ_TRANS_PACKED_0 =
    "\1\4\1\5\1\6\1\7\1\10\1\11\1\12\1\13"+
    "\1\14\1\15\1\16\1\17\1\20\1\21\1\22\1\23"+
    "\1\24\1\4\1\25\1\26\2\25\1\27\2\25\1\30"+
    "\1\31\1\25\1\32\1\25\1\33\1\25\1\34\1\25"+
    "\1\35\1\4\1\36\1\37\1\40\1\41\50\0\4\42"+
    "\1\43\43\42\1\0\1\5\51\0\1\42\46\0\1\42"+
    "\51\0\1\44\43\0\2\45\1\6\1\7\44\45\21\0"+
    "\1\46\42\0\2\47\46\0\2\21\52\0\1\50\47\0"+
    "\1\51\51\0\23\25\25\0\4\25\1\52\16\25\25\0"+
    "\10\25\1\53\1\54\11\25\25\0\5\25\1\55\3\25"+
    "\1\56\11\25\25\0\4\25\1\57\16\25\25\0\17\25"+
    "\1\60\3\25\25\0\4\25\1\61\16\25\25\0\6\25"+
    "\1\62\14\25\25\0\6\25\1\63\14\25\17\0\2\36"+
    "\4\0\21\36\1\0\1\36\7\0\1\64\65\0\15\25"+
    "\1\65\5\25\25\0\3\25\1\66\17\25\25\0\16\25"+
    "\1\67\4\25\25\0\16\25\1\70\4\25\25\0\13\25"+
    "\1\71\7\25\25\0\4\25\1\72\16\25\25\0\7\25"+
    "\1\73\13\25\25\0\4\25\1\74\16\25\25\0\4\25"+
    "\1\75\16\25\25\0\11\25\1\76\11\25\25\0\10\25"+
    "\1\77\12\25\25\0\1\100\22\25\25\0\4\25\1\101"+
    "\16\25\25\0\16\25\1\102\4\25\3\0";

  private static int [] zzUnpacktrans() {
    int [] result = new int[1520];
    int offset = 0;
    offset = zzUnpacktrans(ZZ_TRANS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpacktrans(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      value--;
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /** Error code for "Unknown internal scanner error". */
  private static final int ZZ_UNKNOWN_ERROR = 0;
  /** Error code for "could not match input". */
  private static final int ZZ_NO_MATCH = 1;
  /** Error code for "pushback value was too large". */
  private static final int ZZ_PUSHBACK_2BIG = 2;

  /**
   * Error messages for {@link #ZZ_UNKNOWN_ERROR}, {@link #ZZ_NO_MATCH}, and
   * {@link #ZZ_PUSHBACK_2BIG} respectively.
   */
  private static final String ZZ_ERROR_MSG[] = {
    "Unknown internal scanner error",
    "Error: could not match input",
    "Error: pushback value was too large"
  };

  /**
   * ZZ_ATTRIBUTE[aState] contains the attributes of state {@code aState}
   */
  private static final int [] ZZ_ATTRIBUTE = zzUnpackAttribute();

  private static final String ZZ_ATTRIBUTE_PACKED_0 =
    "\1\0\1\10\1\0\1\11\5\1\4\11\1\1\1\11"+
    "\2\1\1\11\14\1\4\11\1\1\1\11\1\0\1\11"+
    "\1\1\2\11\12\1\1\11\16\1";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[66];
    int offset = 0;
    offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAttribute(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /** Input device. */
  private java.io.Reader zzReader;

  /** Current state of the DFA. */
  private int zzState;

  /** Current lexical state. */
  private int zzLexicalState = YYINITIAL;

  /**
   * This buffer contains the current text to be matched and is the source of the {@link #yytext()}
   * string.
   */
  private char zzBuffer[] = new char[Math.min(ZZ_BUFFERSIZE, zzMaxBufferLen())];

  /** Text position at the last accepting state. */
  private int zzMarkedPos;

  /** Current text position in the buffer. */
  private int zzCurrentPos;

  /** Marks the beginning of the {@link #yytext()} string in the buffer. */
  private int zzStartRead;

  /** Marks the last character in the buffer, that has been read from input. */
  private int zzEndRead;

  /**
   * Whether the scanner is at the end of file.
   * @see #yyatEOF
   */
  private boolean zzAtEOF;

  /**
   * The number of occupied positions in {@link #zzBuffer} beyond {@link #zzEndRead}.
   *
   * <p>When a lead/high surrogate has been read from the input stream into the final
   * {@link #zzBuffer} position, this will have a value of 1; otherwise, it will have a value of 0.
   */
  private int zzFinalHighSurrogate = 0;

  /** Number of newlines encountered up to the start of the matched text. */
  private int yyline;

  /** Number of characters from the last newline up to the start of the matched text. */
  private int yycolumn;

  /** Number of characters up to the start of the matched text. */
  @SuppressWarnings("unused")
  private long yychar;

  /** Whether the scanner is currently at the beginning of a line. */
  @SuppressWarnings("unused")
  private boolean zzAtBOL = true;

  /** Whether the user-EOF-code has already been executed. */
  @SuppressWarnings("unused")
  private boolean zzEOFDone;


  /**
   * Creates a new scanner
   *
   * @param   in  the java.io.Reader to read input from.
   */
  LexicalAnalyzer(java.io.Reader in) {
    this.zzReader = in;
  }


  /** Returns the maximum size of the scanner buffer, which limits the size of tokens. */
  private int zzMaxBufferLen() {
    return Integer.MAX_VALUE;
  }

  /**  Whether the scanner buffer can grow to accommodate a larger token. */
  private boolean zzCanGrow() {
    return true;
  }

  /**
   * Translates raw input code points to DFA table row
   */
  private static int zzCMap(int input) {
    int offset = input & 255;
    return offset == input ? ZZ_CMAP_BLOCKS[offset] : ZZ_CMAP_BLOCKS[ZZ_CMAP_TOP[input >> 8] | offset];
  }

  /**
   * Refills the input buffer.
   *
   * @return {@code false} iff there was new input.
   * @exception java.io.IOException  if any I/O-Error occurs
   */
  private boolean zzRefill() throws java.io.IOException {

    /* first: make room (if you can) */
    if (zzStartRead > 0) {
      zzEndRead += zzFinalHighSurrogate;
      zzFinalHighSurrogate = 0;
      System.arraycopy(zzBuffer, zzStartRead,
                       zzBuffer, 0,
                       zzEndRead - zzStartRead);

      /* translate stored positions */
      zzEndRead -= zzStartRead;
      zzCurrentPos -= zzStartRead;
      zzMarkedPos -= zzStartRead;
      zzStartRead = 0;
    }

    /* is the buffer big enough? */
    if (zzCurrentPos >= zzBuffer.length - zzFinalHighSurrogate && zzCanGrow()) {
      /* if not, and it can grow: blow it up */
      char newBuffer[] = new char[Math.min(zzBuffer.length * 2, zzMaxBufferLen())];
      System.arraycopy(zzBuffer, 0, newBuffer, 0, zzBuffer.length);
      zzBuffer = newBuffer;
      zzEndRead += zzFinalHighSurrogate;
      zzFinalHighSurrogate = 0;
    }

    /* fill the buffer with new input */
    int requested = zzBuffer.length - zzEndRead;
    int numRead = zzReader.read(zzBuffer, zzEndRead, requested);

    /* not supposed to occur according to specification of java.io.Reader */
    if (numRead == 0) {
      if (requested == 0) {
        throw new java.io.EOFException("Scan buffer limit reached ["+zzBuffer.length+"]");
      }
      else {
        throw new java.io.IOException(
            "Reader returned 0 characters. See JFlex examples/zero-reader for a workaround.");
      }
    }
    if (numRead > 0) {
      zzEndRead += numRead;
      if (Character.isHighSurrogate(zzBuffer[zzEndRead - 1])) {
        if (numRead == requested) { // We requested too few chars to encode a full Unicode character
          --zzEndRead;
          zzFinalHighSurrogate = 1;
        } else {                    // There is room in the buffer for at least one more char
          int c = zzReader.read();  // Expecting to read a paired low surrogate char
          if (c == -1) {
            return true;
          } else {
            zzBuffer[zzEndRead++] = (char)c;
          }
        }
      }
      /* potentially more input available */
      return false;
    }

    /* numRead < 0 ==> end of stream */
    return true;
  }


  /**
   * Closes the input reader.
   *
   * @throws java.io.IOException if the reader could not be closed.
   */
  public final void yyclose() throws java.io.IOException {
    zzAtEOF = true; // indicate end of file
    zzEndRead = zzStartRead; // invalidate buffer

    if (zzReader != null) {
      zzReader.close();
    }
  }


  /**
   * Resets the scanner to read from a new input stream.
   *
   * <p>Does not close the old reader.
   *
   * <p>All internal variables are reset, the old input stream <b>cannot</b> be reused (internal
   * buffer is discarded and lost). Lexical state is set to {@code ZZ_INITIAL}.
   *
   * <p>Internal scan buffer is resized down to its initial length, if it has grown.
   *
   * @param reader The new input stream.
   */
  public final void yyreset(java.io.Reader reader) {
    zzReader = reader;
    zzEOFDone = false;
    yyResetPosition();
    zzLexicalState = YYINITIAL;
    int initBufferSize = Math.min(ZZ_BUFFERSIZE, zzMaxBufferLen());
    if (zzBuffer.length > initBufferSize) {
      zzBuffer = new char[initBufferSize];
    }
  }

  /**
   * Resets the input position.
   */
  private final void yyResetPosition() {
      zzAtBOL  = true;
      zzAtEOF  = false;
      zzCurrentPos = 0;
      zzMarkedPos = 0;
      zzStartRead = 0;
      zzEndRead = 0;
      zzFinalHighSurrogate = 0;
      yyline = 0;
      yycolumn = 0;
      yychar = 0L;
  }


  /**
   * Returns whether the scanner has reached the end of the reader it reads from.
   *
   * @return whether the scanner has reached EOF.
   */
  public final boolean yyatEOF() {
    return zzAtEOF;
  }


  /**
   * Returns the current lexical state.
   *
   * @return the current lexical state.
   */
  public final int yystate() {
    return zzLexicalState;
  }


  /**
   * Enters a new lexical state.
   *
   * @param newState the new lexical state
   */
  public final void yybegin(int newState) {
    zzLexicalState = newState;
  }


  /**
   * Returns the text matched by the current regular expression.
   *
   * @return the matched text.
   */
  public final String yytext() {
    return new String(zzBuffer, zzStartRead, zzMarkedPos-zzStartRead);
  }


  /**
   * Returns the character at the given position from the matched text.
   *
   * <p>It is equivalent to {@code yytext().charAt(pos)}, but faster.
   *
   * @param position the position of the character to fetch. A value from 0 to {@code yylength()-1}.
   *
   * @return the character at {@code position}.
   */
  public final char yycharat(int position) {
    return zzBuffer[zzStartRead + position];
  }


  /**
   * How many characters were matched.
   *
   * @return the length of the matched text region.
   */
  public final int yylength() {
    return zzMarkedPos-zzStartRead;
  }


  /**
   * Reports an error that occurred while scanning.
   *
   * <p>In a well-formed scanner (no or only correct usage of {@code yypushback(int)} and a
   * match-all fallback rule) this method will only be called with things that
   * "Can't Possibly Happen".
   *
   * <p>If this method is called, something is seriously wrong (e.g. a JFlex bug producing a faulty
   * scanner etc.).
   *
   * <p>Usual syntax/scanner level error handling should be done in error fallback rules.
   *
   * @param errorCode the code of the error message to display.
   */
  private static void zzScanError(int errorCode) {
    String message;
    try {
      message = ZZ_ERROR_MSG[errorCode];
    } catch (ArrayIndexOutOfBoundsException e) {
      message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
    }

    throw new Error(message);
  }


  /**
   * Pushes the specified amount of characters back into the input stream.
   *
   * <p>They will be read again by then next call of the scanning method.
   *
   * @param number the number of characters to be read again. This number must not be greater than
   *     {@link #yylength()}.
   */
  public void yypushback(int number)  {
    if ( number > yylength() )
      zzScanError(ZZ_PUSHBACK_2BIG);

    zzMarkedPos -= number;
  }




  /**
   * Resumes scanning until the next regular expression is matched, the end of input is encountered
   * or an I/O-Error occurs.
   *
   * @return the next token.
   * @exception java.io.IOException if any I/O-Error occurs.
   */
  public LexicalSymbol nextToken() throws java.io.IOException
    , PatternSyntaxException
  {
    int zzInput;
    int zzAction;

    // cached fields:
    int zzCurrentPosL;
    int zzMarkedPosL;
    int zzEndReadL = zzEndRead;
    char[] zzBufferL = zzBuffer;

    int [] zzTransL = ZZ_TRANS;
    int [] zzRowMapL = ZZ_ROWMAP;
    int [] zzAttrL = ZZ_ATTRIBUTE;

    while (true) {
      zzMarkedPosL = zzMarkedPos;

      boolean zzR = false;
      int zzCh;
      int zzCharCount;
      for (zzCurrentPosL = zzStartRead  ;
           zzCurrentPosL < zzMarkedPosL ;
           zzCurrentPosL += zzCharCount ) {
        zzCh = Character.codePointAt(zzBufferL, zzCurrentPosL, zzMarkedPosL);
        zzCharCount = Character.charCount(zzCh);
        switch (zzCh) {
        case '\u000B':  // fall through
        case '\u000C':  // fall through
        case '\u0085':  // fall through
        case '\u2028':  // fall through
        case '\u2029':
          yyline++;
          yycolumn = 0;
          zzR = false;
          break;
        case '\r':
          yyline++;
          yycolumn = 0;
          zzR = true;
          break;
        case '\n':
          if (zzR)
            zzR = false;
          else {
            yyline++;
            yycolumn = 0;
          }
          break;
        default:
          zzR = false;
          yycolumn += zzCharCount;
        }
      }

      if (zzR) {
        // peek one character ahead if it is
        // (if we have counted one line too much)
        boolean zzPeek;
        if (zzMarkedPosL < zzEndReadL)
          zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        else if (zzAtEOF)
          zzPeek = false;
        else {
          boolean eof = zzRefill();
          zzEndReadL = zzEndRead;
          zzMarkedPosL = zzMarkedPos;
          zzBufferL = zzBuffer;
          if (eof)
            zzPeek = false;
          else
            zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        }
        if (zzPeek) yyline--;
      }
      zzAction = -1;

      zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;

      zzState = ZZ_LEXSTATE[zzLexicalState];

      // set up zzAction for empty match case:
      int zzAttributes = zzAttrL[zzState];
      if ( (zzAttributes & 1) == 1 ) {
        zzAction = zzState;
      }


      zzForAction: {
        while (true) {

          if (zzCurrentPosL < zzEndReadL) {
            zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL, zzEndReadL);
            zzCurrentPosL += Character.charCount(zzInput);
          }
          else if (zzAtEOF) {
            zzInput = YYEOF;
            break zzForAction;
          }
          else {
            // store back cached positions
            zzCurrentPos  = zzCurrentPosL;
            zzMarkedPos   = zzMarkedPosL;
            boolean eof = zzRefill();
            // get translated positions and possibly new buffer
            zzCurrentPosL  = zzCurrentPos;
            zzMarkedPosL   = zzMarkedPos;
            zzBufferL      = zzBuffer;
            zzEndReadL     = zzEndRead;
            if (eof) {
              zzInput = YYEOF;
              break zzForAction;
            }
            else {
              zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL, zzEndReadL);
              zzCurrentPosL += Character.charCount(zzInput);
            }
          }
          int zzNext = zzTransL[ zzRowMapL[zzState] + zzCMap(zzInput) ];
          if (zzNext == -1) break zzForAction;
          zzState = zzNext;

          zzAttributes = zzAttrL[zzState];
          if ( (zzAttributes & 1) == 1 ) {
            zzAction = zzState;
            zzMarkedPosL = zzCurrentPosL;
            if ( (zzAttributes & 8) == 8 ) break zzForAction;
          }

        }
      }

      // store back cached position
      zzMarkedPos = zzMarkedPosL;

      if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
        zzAtEOF = true;
            switch (zzLexicalState) {
            case LONGCOMMENTS: {
              throw new PatternSyntaxException("A comment is never closed.",yytext(),yyline);
            }  // fall though
            case 67: break;
            default:
          { 	return new LexicalSymbol(LexicalUnit.EOS, yyline, yycolumn);
 }
        }
      }
      else {
        switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
          case 1:
            { throw new PatternSyntaxException("Unmatched token, out of symbols",yytext(),yyline);
            }
          // fall through
          case 34: break;
          case 2:
            { 
            }
          // fall through
          case 35: break;
          case 3:
            { return new LexicalSymbol(LexicalUnit.LPAREN, yyline, yycolumn, yytext());
            }
          // fall through
          case 36: break;
          case 4:
            { return new LexicalSymbol(LexicalUnit.RPAREN, yyline, yycolumn, yytext());
            }
          // fall through
          case 37: break;
          case 5:
            { return new LexicalSymbol(LexicalUnit.TIMES, yyline, yycolumn, yytext());
            }
          // fall through
          case 38: break;
          case 6:
            { return new LexicalSymbol(LexicalUnit.PLUS, yyline, yycolumn, yytext());
            }
          // fall through
          case 39: break;
          case 7:
            { return new LexicalSymbol(LexicalUnit.MINUS, yyline, yycolumn, yytext());
            }
          // fall through
          case 40: break;
          case 8:
            { return new LexicalSymbol(LexicalUnit.DIVIDE, yyline, yycolumn, yytext());
            }
          // fall through
          case 41: break;
          case 9:
            { return new LexicalSymbol(LexicalUnit.NUMBER, yyline, yycolumn, Integer.valueOf(yytext()));
            }
          // fall through
          case 42: break;
          case 10:
            { return new LexicalSymbol(LexicalUnit.COLUMN, yyline, yycolumn, yytext());
            }
          // fall through
          case 43: break;
          case 11:
            { return new LexicalSymbol(LexicalUnit.SMALLER, yyline, yycolumn, yytext());
            }
          // fall through
          case 44: break;
          case 12:
            { return new LexicalSymbol(LexicalUnit.ASSIGN, yyline, yycolumn, yytext());
            }
          // fall through
          case 45: break;
          case 13:
            { return new LexicalSymbol(LexicalUnit.PROGNAME,yyline, yycolumn,yytext());
            }
          // fall through
          case 46: break;
          case 14:
            { return new LexicalSymbol(LexicalUnit.VARNAME,yyline, yycolumn,yytext());
            }
          // fall through
          case 47: break;
          case 15:
            { return new LexicalSymbol(LexicalUnit.LBRACK, yyline, yycolumn, yytext());
            }
          // fall through
          case 48: break;
          case 16:
            { return new LexicalSymbol(LexicalUnit.PIPE, yyline, yycolumn, yytext());
            }
          // fall through
          case 49: break;
          case 17:
            { return new LexicalSymbol(LexicalUnit.RBRACK, yyline, yycolumn, yytext());
            }
          // fall through
          case 50: break;
          case 18:
            { yybegin(LONGCOMMENTS);
            }
          // fall through
          case 51: break;
          case 19:
            { return new LexicalSymbol(LexicalUnit.IMPLIES, yyline, yycolumn, yytext());
            }
          // fall through
          case 52: break;
          case 20:
            { System.err.println("Warning! Numbers with leading zeros are deprecated: " + yytext()); return new LexicalSymbol(LexicalUnit.NUMBER, yyline, yycolumn, Integer.valueOf(yytext()));
            }
          // fall through
          case 53: break;
          case 21:
            { return new LexicalSymbol(LexicalUnit.SMALEQ, yyline, yycolumn, yytext());
            }
          // fall through
          case 54: break;
          case 22:
            { return new LexicalSymbol(LexicalUnit.EQUAL, yyline, yycolumn, yytext());
            }
          // fall through
          case 55: break;
          case 23:
            { return new LexicalSymbol(LexicalUnit.BE, yyline, yycolumn, yytext());
            }
          // fall through
          case 56: break;
          case 24:
            { return new LexicalSymbol(LexicalUnit.IF, yyline, yycolumn, yytext());
            }
          // fall through
          case 57: break;
          case 25:
            { return new LexicalSymbol(LexicalUnit.INPUT, yyline, yycolumn, yytext());
            }
          // fall through
          case 58: break;
          case 26:
            { yybegin(YYINITIAL);
            }
          // fall through
          case 59: break;
          case 27:
            { return new LexicalSymbol(LexicalUnit.END, yyline, yycolumn, yytext());
            }
          // fall through
          case 60: break;
          case 28:
            { return new LexicalSymbol(LexicalUnit.LET, yyline, yycolumn, yytext());
            }
          // fall through
          case 61: break;
          case 29:
            { return new LexicalSymbol(LexicalUnit.OUTPUT, yyline, yycolumn, yytext());
            }
          // fall through
          case 62: break;
          case 30:
            { return new LexicalSymbol(LexicalUnit.ELSE, yyline, yycolumn, yytext());
            }
          // fall through
          case 63: break;
          case 31:
            { return new LexicalSymbol(LexicalUnit.THEN, yyline, yycolumn, yytext());
            }
          // fall through
          case 64: break;
          case 32:
            { return new LexicalSymbol(LexicalUnit.WHILE, yyline, yycolumn, yytext());
            }
          // fall through
          case 65: break;
          case 33:
            { return new LexicalSymbol(LexicalUnit.REPEAT, yyline, yycolumn, yytext());
            }
          // fall through
          case 66: break;
          default:
            zzScanError(ZZ_NO_MATCH);
        }
      }
    }
  }


}
