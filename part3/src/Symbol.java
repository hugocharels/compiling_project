/**
 * Represents a symbol in the grammar.
 */
public interface Symbol {

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