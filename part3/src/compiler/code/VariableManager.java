package compiler.code;

import java.util.HashMap;
import java.util.Map;

public class VariableManager {
	// Singleton instance
	private static VariableManager instance;

	// Map to store variable names and their allocation status
	private final Map<String, Boolean> variableMap;

	// Counter for generating unique variables
	private int conditionCounter;

	// Private constructor to prevent instantiation
	private VariableManager() {
		this.variableMap = new HashMap<>();
		this.conditionCounter = 0;
	}

	// Method to get the singleton instance
	public static VariableManager getInstance() {
		if (instance == null) {
			instance = new VariableManager();
		}
		return instance;
	}

	// Method to check if a variable is allocated
	public boolean isDeclared(String variableName) {
		return variableMap.getOrDefault(variableName, false) ||
				variableMap.getOrDefault(variableName.replace("%", ""), false);
	}

	// Method to allocate a variable
	public void declare(String variableName) {
		variableMap.put(variableName, true);
	}

	// Method to deallocate a variable
	public void deallocateVariable(String variableName) {
		variableMap.put(variableName, false);
	}

	// Method to get a unique variable for conditions
	public String getUniqueConditionVariable() {
		String uniqueVar = "cond_var_" + conditionCounter++;
		declare(uniqueVar); // Mark it as allocated
		return uniqueVar;
	}
}
