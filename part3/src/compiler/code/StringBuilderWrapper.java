package compiler.code;

public class StringBuilderWrapper {

	private final StringBuilder stringBuilder = new StringBuilder();
	private int indentLevel = 0;
	private boolean newLine = true;
	private int tempVarCounter = 0;

	public StringBuilderWrapper appendParagraph(String preamble) {
		stringBuilder.append(preamble);
		return this;
	}

	public StringBuilderWrapper append(String str) {
		if (newLine) {
			stringBuilder.append("\t".repeat(this.indentLevel));
			newLine = false;
		}
		stringBuilder.append(str);
		return this;
	}

	public StringBuilderWrapper appendln(String str) {
		if (newLine) {
			stringBuilder.append("\t".repeat(this.indentLevel));
		}
		stringBuilder.append(str).append("\n");
		newLine = true;
		return this;
	}

	public StringBuilderWrapper appendln() {
		stringBuilder.append("\n");
		newLine = true;
		return this;
	}

	public void incrementIndentLevel() {
		++this.indentLevel;
	}

	public void decrementIndentLevel() {
		--this.indentLevel;
	}

	@Override
	public String toString() {
		return this.stringBuilder.toString();
	}

	public void clear() {
		this.stringBuilder.setLength(0);
		this.indentLevel = 0;
		this.newLine = true;
	}

	public String createTempVar() {
		return "%T" + (tempVarCounter++);
	}
}
