package compiler;

/**
 * Represents a symbol in the grammar.
 */
public interface Symbol {

	/**
	 * Compares this symbol to the specified object.
	 *
	 * @param obj the object to compare this symbol against
	 * @return true if the specified object is equal to this symbol, false otherwise
	 */
	@Override
	boolean equals(Object obj);

	/**
	 * Returns a string representation of the symbol.
	 *
	 * @return a string representation of the symbol
	 */
	@Override
	String toString();

	/**
	 * Returns the LaTeX representation of the symbol.
	 *
	 * @return the LaTeX representation of the symbol
	 */
	String toLatex();
}