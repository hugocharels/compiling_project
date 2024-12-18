package compiler.exceptions;

public class UnexpectedTerminalException extends ParsingException {
	public UnexpectedTerminalException(String message) {
		super(message);
	}
}
