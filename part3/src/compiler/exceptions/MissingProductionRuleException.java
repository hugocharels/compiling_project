package compiler.exceptions;

/**
 * Exception thrown when a required production rule is missing during parsing.
 */
public class MissingProductionRuleException extends ParsingException {

	/**
	 * Constructs a new MissingProductionRuleException with the specified detail message.
	 *
	 * @param message the detail message
	 */
	public MissingProductionRuleException(String message) {
		super(message);
	}
}