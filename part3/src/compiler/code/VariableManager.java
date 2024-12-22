package compiler.code;

import java.util.HashSet;
import java.util.Set;

/**
 * Manages variable declarations using a singleton pattern.
 */
public class VariableManager {
	// Singleton instance
	private static VariableManager instance;

	// Set to store variable names and their allocation status
	private final Set<String> variableSet;

	// Private constructor to prevent instantiation
	private VariableManager() {
		this.variableSet = new HashSet<>();
	}

	/**
	 * Returns the singleton instance of VariableManager.
	 *
	 * @return the singleton instance
	 */
	public static VariableManager getInstance() {
		if (instance == null) {
			instance = new VariableManager();
		}
		return instance;
	}

	/**
	 * Checks if a variable is declared.
	 *
	 * @param variableName the name of the variable
	 * @return true if the variable is declared, false otherwise
	 */
	public boolean isDeclared(String variableName) {
		return variableSet.contains(variableName) ||
				variableSet.contains(variableName.replace("%", ""));
	}

	/**
	 * Declares a variable.
	 *
	 * @param variableName the name of the variable to declare
	 */
	public void declare(String variableName) {
		variableSet.add(variableName);
	}
}
