package compiler.exceptions;

/**
 * Exception thrown when a compilation error occurs.
 */
public class CompilationException extends RuntimeException {
	/**
	 * Constructs a new CompilationException with the specified detail message.
	 *
	 * @param message the detail message
	 */
	public CompilationException(String message) {
		super(message);
	}
}