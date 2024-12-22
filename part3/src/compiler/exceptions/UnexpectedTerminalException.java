package compiler.exceptions;

/**
 * This exception is thrown when an unexpected terminal is encountered during parsing.
 */
public class UnexpectedTerminalException extends ParsingException {
	/**
	 * Constructs a new UnexpectedTerminalException with the specified detail message.
	 *
	 * @param message the detail message
	 */
	public UnexpectedTerminalException(String message) {
		super(message);
	}
}
