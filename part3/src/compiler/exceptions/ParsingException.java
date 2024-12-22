package compiler.exceptions;

/**
 * Exception thrown when a parsing error occurs.
 */
public class ParsingException extends Exception {
	/**
	 * Constructs a new ParsingException with the specified detail message.
	 *
	 * @param message the detail message
	 */
	public ParsingException(String message) {
		super(message);
	}
}

