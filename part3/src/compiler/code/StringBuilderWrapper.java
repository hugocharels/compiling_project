package compiler.code;

/**
 * A wrapper class for StringBuilder that provides additional functionality for generating code.
 */
public class StringBuilderWrapper {

	// The underlying StringBuilder instance.
	private final StringBuilder stringBuilder = new StringBuilder();

	// The current indentation level.
	private int indentLevel = 0;

	// Flag indicating if a new line should be started.
	private boolean newLine = true;

	// Counter for generating temporary variable names.
	private int tempVarCounter = 0;

	/**
	 * Appends a preamble to the StringBuilder.
	 *
	 * @param preamble the string to append
	 * @return the current StringBuilderWrapper instance
	 */
	public StringBuilderWrapper appendParagraph(String preamble) {
		stringBuilder.append(preamble);
		return this;
	}

	/**
	 * Appends a string to the StringBuilder, adding indentation if starting a new line.
	 *
	 * @param str the string to append
	 * @return the current StringBuilderWrapper instance
	 */
	public StringBuilderWrapper append(String str) {
		if (newLine) {
			stringBuilder.append("\t".repeat(this.indentLevel));
			newLine = false;
		}
		stringBuilder.append(str);
		return this;
	}

	/**
	 * Appends a string to the StringBuilder, followed by a newline, adding indentation if starting a new line.
	 *
	 * @param str the string to append
	 * @return the current StringBuilderWrapper instance
	 */
	public StringBuilderWrapper appendln(String str) {
		if (newLine) {
			stringBuilder.append("\t".repeat(this.indentLevel));
		}
		stringBuilder.append(str).append("\n");
		newLine = true;
		return this;
	}

	/**
	 * Appends a newline to the StringBuilder.
	 *
	 * @return the current StringBuilderWrapper instance
	 */
	public StringBuilderWrapper appendln() {
		stringBuilder.append("\n");
		newLine = true;
		return this;
	}

	/**
	 * Increments the indentation level.
	 */
	public void incrementIndentLevel() {
		++this.indentLevel;
	}

	/**
	 * Decrements the indentation level.
	 */
	public void decrementIndentLevel() {
		--this.indentLevel;
	}

	/**
	 * Returns the string representation of the current StringBuilder content.
	 *
	 * @return the string content
	 */
	@Override
	public String toString() {
		return this.stringBuilder.toString();
	}

	/**
	 * Clears the StringBuilder and resets the state.
	 */
	public void clear() {
		this.stringBuilder.setLength(0);
		this.indentLevel = 0;
		this.newLine = true;
	}

	/**
	 * Creates a new temporary variable name.
	 *
	 * @return the temporary variable name
	 */
	public String createTempVar() {
		return "%T" + (tempVarCounter++);
	}
}